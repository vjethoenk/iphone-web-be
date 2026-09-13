package com.example.iphone_web_be.modules.category.mapper;

import com.example.iphone_web_be.modules.category.dto.request.CategoryRequest;
import com.example.iphone_web_be.modules.category.dto.response.CategoryResponse;
import com.example.iphone_web_be.modules.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);
    CategoryResponse toCategoryResponse(Category Category);

    void updateCategory(@MappingTarget Category category , CategoryRequest request);
}
