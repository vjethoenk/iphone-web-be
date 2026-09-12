package com.example.iphone_web_be.modules.products.mapper;

import com.example.iphone_web_be.modules.category.dto.response.CategoryResponse;
import com.example.iphone_web_be.modules.category.entity.Category;
import com.example.iphone_web_be.modules.products.dto.request.ProductCreationRequest;
import com.example.iphone_web_be.modules.products.dto.request.ProductImageRequest;
import com.example.iphone_web_be.modules.products.dto.request.ProductSpecificationRequest;
import com.example.iphone_web_be.modules.products.dto.request.ProductVariantRequest;
import com.example.iphone_web_be.modules.products.dto.response.*;
import com.example.iphone_web_be.modules.products.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Product toProduct(ProductCreationRequest request);

    ProductResponse toProductResponse(Product product);

    ProductBaseResponse toProductBaseResponse(Product product);

    @Mapping(target = "product", ignore = true)
    ProductSpecification toProductSpecification(ProductSpecificationRequest request);

    ProductSpecificationResponse toProductSpecificationResponse(ProductSpecification specification);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "color", ignore = true)
    @Mapping(target = "storage", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    ProductVariant toProductVariant(ProductVariantRequest request);

    ProductVariantResponse toProductVariantResponse(ProductVariant variant);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "color", ignore = true)
    @Mapping(target = "variant", ignore = true)
    ProductImage toProductImage(ProductImageRequest request);

    @Mapping(source = "color.id", target = "colorId")
    @Mapping(source = "variant.id", target = "variantId")
    ProductImageResponse toProductImageResponse(ProductImage image);

    ColorResponse toColorResponse(Color color);

    StorageResponse toStorageResponse(Storage storage);

    CategoryResponse toCategoryResponse(Category category);
}
