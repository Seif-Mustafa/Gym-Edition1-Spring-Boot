package com.gym.edition1.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gym.edition1.dto.request.user.CreateUserRequest;
import com.gym.edition1.dto.request.user.UpdateUserRequest;
import com.gym.edition1.dto.response.UserLoginResponse;
import com.gym.edition1.model.User;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final PasswordEncoder passwordEncoder;


  
  public UserLoginResponse toDto(User user, String token) {
    return UserLoginResponse.builder()
        .userId(user.getUserId())
        .gymId(user.getGymId())
        .username(user.getUsername())
        .type(user.getType())
        .token(token)
        .build();
  }

  public User toEntity(CreateUserRequest createUserRequest){
    User user = new User();
    user.setUsername(createUserRequest.getUsername());
    user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
    user.setType(createUserRequest.getType());
    user.setGymId(createUserRequest.getGymId());
    user.setCreatedBy(createUserRequest.getCreatedBy());
    user.setCreatedOn(LocalDateTime.now());
    return user;
  }

  public User toEntity(User user, UpdateUserRequest updateUserRequest){
    if(updateUserRequest.getUsername() != null){
      user.setUsername(updateUserRequest.getUsername());
    }
    if(updateUserRequest.getPassword() != null){
      user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
    }
    if(updateUserRequest.getType() != null){
      user.setType(updateUserRequest.getType());
    }
    if(updateUserRequest.getGymId() != null){
      user.setGymId(updateUserRequest.getGymId());
    }
      user.setModifiedBy(updateUserRequest.getModifiedBy());

    user.setModifiedOn(LocalDateTime.now());
    return user;
  }
}
