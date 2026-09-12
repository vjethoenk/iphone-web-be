package com.example.iphone_web_be.modules.products.service;

import com.example.iphone_web_be.common.enums.ProductStatus;
import com.example.iphone_web_be.exception.AppException;
import com.example.iphone_web_be.exception.ErrorCode;
import com.example.iphone_web_be.modules.category.entity.Category;
import com.example.iphone_web_be.modules.category.repository.CategoryRepository;
import com.example.iphone_web_be.modules.products.dto.request.ProductCreationRequest;
import com.example.iphone_web_be.modules.products.dto.request.ProductImageRequest;
import com.example.iphone_web_be.modules.products.dto.request.ProductVariantRequest;
import com.example.iphone_web_be.modules.products.dto.response.*;
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
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        String slug = StringUtils.hasText(request.getSlug())
                ? generateSlug(request.getSlug())
                : generateSlug(request.getName());

        if (productRepository.existsBySlug(slug)) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }

        //Map và lưu Product
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

        //Lưu Thông số kỹ thuật (Specification)
        ProductSpecificationResponse specResponse = null;
        if (request.getSpecification() != null) {
            ProductSpecification specification = productMapper.toProductSpecification(request.getSpecification());
            specification.setProduct(savedProduct);
            ProductSpecification savedSpec = productSpecificationRepository.save(specification);
            specResponse = productMapper.toProductSpecificationResponse(savedSpec);
        }

        // Lưu Các biến thể sản phẩm (Variants)
        List<ProductVariantResponse> variantResponses = new ArrayList<>();
        Map<String, ProductVariant> createdVariantsMap = new HashMap<>();
        Set<String> variantUniqueKeys = new HashSet<>();

        if (request.getVariants() != null && !request.getVariants().isEmpty()) {
            for (ProductVariantRequest variantReq : request.getVariants()) {
                String uniqueKey = variantReq.getColorId() + "_" + variantReq.getStorageId();
                if (!variantUniqueKeys.add(uniqueKey)) {
                    throw new AppException(ErrorCode.PRODUCT_EXISTED);
                }

                Color color = colorRepository.findById(variantReq.getColorId())
                        .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOT_FOUND));

                Storage storage = storageRepository.findById(variantReq.getStorageId())
                        .orElseThrow(() -> new AppException(ErrorCode.STORAGE_NOT_FOUND));

                String generatedSku = StringUtils.hasText(variantReq.getSku())
                        ? variantReq.getSku()
                        : generateSku(savedProduct.getName(), color.getName(), storage.getName());

                if (productVariantRepository.existsBySku(generatedSku)) {
                    throw new AppException(ErrorCode.SKU_EXISTED);
                }

                ProductVariant variant = productMapper.toProductVariant(variantReq);
                variant.setProduct(savedProduct);
                variant.setColor(color);
                variant.setStorage(storage);
                variant.setSku(generatedSku);

                ProductVariant savedVariant = productVariantRepository.save(variant);
                createdVariantsMap.put(savedVariant.getId(), savedVariant);
                variantResponses.add(productMapper.toProductVariantResponse(savedVariant));
            }
        }

        // Lưu Các hình ảnh sản phẩm (Images)
        List<ProductImageResponse> imageResponses = new ArrayList<>();
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            for (ProductImageRequest imageReq : request.getImages()) {
                ProductImage image = productMapper.toProductImage(imageReq);
                image.setProduct(savedProduct);

                if (StringUtils.hasText(imageReq.getColorId())) {
                    Color color = colorRepository.findById(imageReq.getColorId())
                            .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOT_FOUND));
                    image.setColor(color);
                }

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

        ProductResponse response = productMapper.toProductResponse(savedProduct);
        response.setSpecification(specResponse);
        response.setVariants(variantResponses);
        response.setImages(imageResponses);

        return response;
    }

    @Transactional(readOnly = true)
    public ProductDetailResponse getProductDetail(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        List<ProductVariant> variants = productVariantRepository.findByProductId(id);
        List<ProductImage> images = productImageRepository.findByProductId(id);
        ProductSpecification spec = productSpecificationRepository.findByProductId(id).orElse(null);

        Map<String, Color> colorMap = new LinkedHashMap<>();
        Map<String, Storage> storageMap = new LinkedHashMap<>();

        List<ProductVariantResponse> variantResponses = new ArrayList<>();
        for (ProductVariant v : variants) {
            colorMap.put(v.getColor().getId(), v.getColor());
            storageMap.put(v.getStorage().getId(), v.getStorage());
            variantResponses.add(productMapper.toProductVariantResponse(v));
        }

        // Cũng thu thập Color từ images nếu image có colorId mà không có variant
        for (ProductImage img : images) {
            if (img.getColor() != null) {
                colorMap.put(img.getColor().getId(), img.getColor());
            }
        }

        List<ProductColorGalleryResponse> colorGalleries = new ArrayList<>();
        for (Color color : colorMap.values()) {
            List<ProductImageResponse> colorImages = images.stream()
                    .filter(img -> img.getColor() != null && img.getColor().getId().equals(color.getId()))
                    .map(productMapper::toProductImageResponse)
                    .toList();

            colorGalleries.add(ProductColorGalleryResponse.builder()
                    .id(color.getId())
                    .name(color.getName())
                    .hexCode(color.getHexCode())
                    .images(colorImages)
                    .build());
        }

        List<StorageResponse> storageResponses = storageMap.values().stream()
                .map(productMapper::toStorageResponse)
                .toList();

        List<ProductImageResponse> generalImages = images.stream()
                .filter(img -> img.getColor() == null)
                .map(productMapper::toProductImageResponse)
                .toList();

        ProductResponse baseResponse = productMapper.toProductResponse(product);

        return ProductDetailResponse.builder()
                .id(product.getId())
                .category(baseResponse.getCategory())
                .name(product.getName())
                .slug(product.getSlug())
                .brand(product.getBrand())
                .shortDescription(product.getShortDescription())
                .description(product.getDescription())
                .thumbnail(product.getThumbnail())
                .status(product.getStatus())
                .featured(product.isFeatured())
                .specification(spec != null ? productMapper.toProductSpecificationResponse(spec) : null)
                .colors(colorGalleries)
                .storages(storageResponses)
                .variants(variantResponses)
                .images(generalImages)
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    private String generateSku(String productName, String colorName, String storageName) {
        String pCode = generateCode(productName);
        String sCode = generateCode(storageName);
        String cCode = generateCode(colorName);
        return (pCode + "-" + sCode + "-" + cCode).toUpperCase(Locale.ENGLISH);
    }

    private String generateCode(String input) {
        if (!StringUtils.hasText(input)) return "X";
        String normalized = generateSlug(input);
        return normalized.toUpperCase(Locale.ENGLISH);
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
