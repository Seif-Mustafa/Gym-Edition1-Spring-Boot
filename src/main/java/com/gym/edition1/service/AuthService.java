package com.gym.edition1.service;

import com.gym.edition1.dto.response.UserLoginResponse;

public interface AuthService {
  UserLoginResponse userLogin(String username, String password);
}
