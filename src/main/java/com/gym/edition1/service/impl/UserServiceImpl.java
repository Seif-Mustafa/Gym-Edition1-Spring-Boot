package com.gym.edition1.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gym.edition1.dto.request.user.CreateUserRequest;
import com.gym.edition1.dto.request.user.UpdateUserRequest;
import com.gym.edition1.mapper.UserMapper;
import com.gym.edition1.model.User;
import com.gym.edition1.repository.UserRepository;
import com.gym.edition1.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public User createUser(CreateUserRequest createUserRequest) {
    User savedUser = userRepository.save(userMapper.toEntity(createUserRequest));
    return savedUser;
  }

  @Override
  public User getUserById(Long userId) {
    return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  public User updateUser(Long userId, UpdateUserRequest updateUserRequest) {
    User existingUser = getUserById(userId);
    User updatedUser = userRepository.save(userMapper.toEntity(existingUser, updateUserRequest));
    return updatedUser;
  }

  @Override
  public void deleteUser(Long userId) {
    User existingUser = getUserById(userId);
    userRepository.delete(existingUser);
  }

  @Override
  public List<User> getUsersByFilters(String username, String type, String gymName,
      Long gymId, Long createdBy, Long modifiedBy) {
    return userRepository.getUsersByFilters(username, type, gymName, gymId, createdBy, modifiedBy);
  }

}
