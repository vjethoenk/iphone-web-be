package com.example.iphone_web_be.modules.products.service;

import com.example.iphone_web_be.exception.AppException;
import com.example.iphone_web_be.exception.ErrorCode;
import com.example.iphone_web_be.modules.products.dto.request.StorageRequest;
import com.example.iphone_web_be.modules.products.dto.response.StorageResponse;
import com.example.iphone_web_be.modules.products.entity.Storage;
import com.example.iphone_web_be.modules.products.mapper.StorageMapper;
import com.example.iphone_web_be.modules.products.repository.StorageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StorageService {
    StorageRepository storageRepository;
    StorageMapper storageMapper;

    public StorageResponse createStorage(StorageRequest request) {

        if (storageRepository.existsByName(request.getName().trim())) {
            throw new AppException(ErrorCode.COLOR_EXISTED);
        }

        Storage storage = storageMapper.toStorage(request);

        return storageMapper.toStorageResponse(
                storageRepository.save(storage)
        );
    }

    public List<StorageResponse> getAll(){
        return storageRepository.findAll().stream().map(storageMapper::toStorageResponse).toList();
    }
}
