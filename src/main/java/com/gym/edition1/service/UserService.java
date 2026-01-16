package com.gym.edition1.service;

import java.util.List;

import com.gym.edition1.dto.request.user.CreateUserRequest;
import com.gym.edition1.dto.request.user.UpdateUserRequest;
import com.gym.edition1.model.User;

public interface UserService {
  User createUser(CreateUserRequest createUserRequest);

  User getUserById(Long userId);

  User updateUser(Long userId, UpdateUserRequest updateUserRequest);

  void deleteUser(Long userId);

  List<User> getUsersByFilters(String username, String type, String gymName,
      Long gymId, Long createdBy, Long modifiedBy);
}
