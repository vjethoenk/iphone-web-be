package com.example.iphone_web_be.modules.category.service;

import com.example.iphone_web_be.common.enums.ProductStatus;
import com.example.iphone_web_be.common.utils.SlugUtils;
import com.example.iphone_web_be.exception.AppException;
import com.example.iphone_web_be.exception.ErrorCode;
import com.example.iphone_web_be.modules.category.dto.request.CategoryRequest;
import com.example.iphone_web_be.modules.category.dto.response.CategoryResponse;
import com.example.iphone_web_be.modules.category.entity.Category;
import com.example.iphone_web_be.modules.category.mapper.CategoryMapper;
import com.example.iphone_web_be.modules.category.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryMapper categoryMapper;
    CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest request) {

        if (categoryRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        String slug = request.getSlug();

        if (slug == null || slug.isBlank()) {
            slug = SlugUtils.generateSlug(request.getName());
        }

        if (categoryRepository.existsBySlug(slug)) {
            throw new AppException(ErrorCode.SLUG_EXISTED);
        }

        Category category = categoryMapper.toCategory(request);
        category.setSlug(slug);

        return categoryMapper.toCategoryResponse(
                categoryRepository.save(category)
        );
    }

    public CategoryResponse getBySlug(String slug) {
        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new AppException(
                        ErrorCode.CATEGORY_NOT_EXISTED
                ));

        return categoryMapper.toCategoryResponse(category);
    }

    public List<CategoryResponse> getAll (){
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).toList();
    }

    public CategoryResponse getById(String id, CategoryRequest request){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_EXISTED));

        categoryMapper.updateCategory(category, request);

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }
}
