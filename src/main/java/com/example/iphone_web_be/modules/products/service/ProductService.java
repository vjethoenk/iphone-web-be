package com.example.iphone_web_be.modules.products.service;

import com.example.iphone_web_be.common.enums.ProductStatus;
import com.example.iphone_web_be.exception.AppException;
import com.example.iphone_web_be.exception.ErrorCode;
import com.example.iphone_web_be.modules.category.entity.Category;
import com.example.iphone_web_be.modules.category.repository.CategoryRepository;
import com.example.iphone_web_be.modules.products.dto.request.ProductCreationRequest;
import com.example.iphone_web_be.modules.products.dto.request.ProductImageRequest;
import com.example.iphone_web_be.modules.products.dto.request.ProductVariantRequest;
import com.example.iphone_web_be.modules.products.dto.response.ProductImageResponse;
import com.example.iphone_web_be.modules.products.dto.response.ProductResponse;
import com.example.iphone_web_be.modules.products.dto.response.ProductSpecificationResponse;
import com.example.iphone_web_be.modules.products.dto.response.ProductVariantResponse;
import com.example.iphone_web_be.modules.products.entity.*;
import com.example.iphone_web_be.modules.products.mapper.ProductMapper;
import com.example.iphone_web_be.modules.products.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductRepository productRepository;
    ProductVariantRepository productVariantRepository;
    ProductSpecificationRepository productSpecificationRepository;
    ProductImageRepository productImageRepository;
    CategoryRepository categoryRepository;
    ColorRepository colorRepository;
    StorageRepository storageRepository;
    ProductMapper productMapper;

    @Transactional
    public ProductResponse createProduct(ProductCreationRequest request) {
        // 1. Kiểm tra danh mục
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        // 2. Xử lý và kiểm tra slug
        String slug = StringUtils.hasText(request.getSlug())
                ? generateSlug(request.getSlug())
                : generateSlug(request.getName());

        if (productRepository.existsBySlug(slug)) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }

        // 3. Map và lưu Product
        Product product = productMapper.toProduct(request);
        product.setCategory(category);
        product.setSlug(slug);

        if (!StringUtils.hasText(product.getBrand())) {
            product.setBrand("Apple");
        }
        if (product.getStatus() == null) {
            product.setStatus(ProductStatus.DRAFT);
        }

        Product savedProduct = productRepository.save(product);

        // 4. Lưu Thông số kỹ thuật (Specification) nếu có
        ProductSpecificationResponse specResponse = null;
        if (request.getSpecification() != null) {
            ProductSpecification specification = productMapper.toProductSpecification(request.getSpecification());
            specification.setProduct(savedProduct);
            ProductSpecification savedSpec = productSpecificationRepository.save(specification);
            specResponse = productMapper.toProductSpecificationResponse(savedSpec);
        }

        // 5. Lưu Các biến thể sản phẩm (Variants) nếu có
        List<ProductVariantResponse> variantResponses = new ArrayList<>();
        Map<String, ProductVariant> createdVariantsMap = new HashMap<>();

        if (request.getVariants() != null && !request.getVariants().isEmpty()) {
            for (ProductVariantRequest variantReq : request.getVariants()) {
                if (productVariantRepository.existsBySku(variantReq.getSku())) {
                    throw new AppException(ErrorCode.SKU_EXISTED);
                }

                Color color = colorRepository.findById(variantReq.getColorId())
                        .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOT_FOUND));

                Storage storage = storageRepository.findById(variantReq.getStorageId())
                        .orElseThrow(() -> new AppException(ErrorCode.STORAGE_NOT_FOUND));

                ProductVariant variant = productMapper.toProductVariant(variantReq);
                variant.setProduct(savedProduct);
                variant.setColor(color);
                variant.setStorage(storage);

                ProductVariant savedVariant = productVariantRepository.save(variant);
                createdVariantsMap.put(savedVariant.getId(), savedVariant);
                variantResponses.add(productMapper.toProductVariantResponse(savedVariant));
            }
        }

        // 6. Lưu Các hình ảnh sản phẩm (Images) nếu có
        List<ProductImageResponse> imageResponses = new ArrayList<>();
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            for (ProductImageRequest imageReq : request.getImages()) {
                ProductImage image = productMapper.toProductImage(imageReq);
                image.setProduct(savedProduct);

                if (StringUtils.hasText(imageReq.getVariantId())) {
                    ProductVariant variant = createdVariantsMap.containsKey(imageReq.getVariantId())
                            ? createdVariantsMap.get(imageReq.getVariantId())
                            : productVariantRepository.findById(imageReq.getVariantId())
                            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
                    image.setVariant(variant);
                }

                ProductImage savedImage = productImageRepository.save(image);
                imageResponses.add(productMapper.toProductImageResponse(savedImage));
            }
        }

        // 7. Tạo Response trả về
        ProductResponse response = productMapper.toProductResponse(savedProduct);
        response.setSpecification(specResponse);
        response.setVariants(variantResponses);
        response.setImages(imageResponses);

        return response;
    }
    public String generateSlug(String input) {
        if (input == null) return "";
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String slug = Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(normalized).replaceAll("");
        slug = slug.toLowerCase(Locale.ENGLISH)
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("\\s+", "-");
        return slug;
    }
}
