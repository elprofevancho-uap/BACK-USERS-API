package com.uap.user.usecase;

import com.uap.user.dto.model.UserRequest;
import com.uap.user.dto.model.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> findAllUsers(Optional<String> fullName);
    UserResponse findById(String externalId);
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(String id, UserRequest request);
    void delete(String id);

}
