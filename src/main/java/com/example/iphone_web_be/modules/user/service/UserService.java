package com.example.iphone_web_be.modules.user.service;

import com.example.iphone_web_be.common.constants.PredefinedRole;
import com.example.iphone_web_be.exception.AppException;
import com.example.iphone_web_be.exception.ErrorCode;
import com.example.iphone_web_be.modules.role.entity.Role;
import com.example.iphone_web_be.modules.role.repository.RoleRepository;
import com.example.iphone_web_be.modules.user.dto.request.UserCreateRequest;
import com.example.iphone_web_be.modules.user.dto.request.UserUpdateRequest;
import com.example.iphone_web_be.modules.user.dto.response.UserResponse;
import com.example.iphone_web_be.modules.user.entity.User;
import com.example.iphone_web_be.modules.user.enums.UserStatus;
import com.example.iphone_web_be.modules.user.mapper.UserMapper;
import com.example.iphone_web_be.modules.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreateRequest request){
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);

        user.setRoles(roles);
        user.setStatus(UserStatus.ACTIVE);
        user.setCustomerCode(generateCustomerCode());

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getAll(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }


    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    private String generateCustomerCode() {
        String code;

        do {
            code = "KH" + String.format("%08d",
                    ThreadLocalRandom.current().nextInt(10000000, 100000000));
        } while (userRepository.existsByCustomerCode(code));

        return code;
    }
}
