package com.gym.edition1.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gym.edition1.dto.response.UserLoginResponse;
import com.gym.edition1.mapper.UserMapper;
import com.gym.edition1.model.User;
import com.gym.edition1.repository.UserRepository;
import com.gym.edition1.service.AuthService;
import com.gym.edition1.util.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  @Override
  public UserLoginResponse userLogin(String username, String password) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new RuntimeException("User not found");
    }

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("Password is wrong");
    }
    String token = jwtService.generateToken(user.getUsername());

    return userMapper.toDto(user,token);
  }
}
