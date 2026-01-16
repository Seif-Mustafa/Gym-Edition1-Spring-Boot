package com.gym.edition1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gym.edition1.dto.generic.ApiResponse;
import com.gym.edition1.dto.response.UserLoginResponse;
import com.gym.edition1.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public ApiResponse<UserLoginResponse> userLogin(@RequestParam String username, @RequestParam String password) {
    return ApiResponse.success(authService.userLogin(username, password),"User Logged In Successfully");
  }

}
