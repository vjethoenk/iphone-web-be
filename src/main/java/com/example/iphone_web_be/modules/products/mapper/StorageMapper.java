package com.example.iphone_web_be.modules.products.mapper;

import com.example.iphone_web_be.modules.products.dto.request.StorageRequest;
import com.example.iphone_web_be.modules.products.dto.response.StorageResponse;
import com.example.iphone_web_be.modules.products.entity.Storage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorageMapper {
    Storage toStorage(StorageRequest request);
    StorageResponse toStorageResponse(Storage storage);
}
