package com.gym.edition1.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.gym.edition1.dto.generic.ApiResponse;
import com.gym.edition1.dto.request.user.CreateUserRequest;
import com.gym.edition1.dto.request.user.UpdateUserRequest;
import com.gym.edition1.model.User;
import com.gym.edition1.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ApiResponse<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
    return ApiResponse.created(userService.createUser(createUserRequest), "User Created Successfully");
  }

  @GetMapping("/{id}")
  public ApiResponse<User> getUserById(@PathVariable Long id) {
    return ApiResponse.success(userService.getUserById(id), "User Retrieved Successfully");
  }

  @PutMapping("/{userId}")
  public ApiResponse<User> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
    return ApiResponse.success(userService.updateUser(userId, updateUserRequest), "User Updated Successfully");
  }

  @DeleteMapping("/{userId}")
  public ApiResponse<Void> deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
    return ApiResponse.noContent("User Deleted Successfully");
  }

  @GetMapping
  public ApiResponse<List<User>> getUsersByFilters(
      @RequestParam(name = "username", required = false) String username,
      @RequestParam(name = "type", required = false) String type,
      @RequestParam(name = "gymName", required = false) String gymName,
      @RequestParam(name = "gymId", required = false) Long gymId,
      @RequestParam(name = "createdBy", required = false) Long createdBy,
      @RequestParam(name = "modifiedBy", required = false) Long modifiedBy) {
    return ApiResponse.success(userService.getUsersByFilters(username,
        type,
        gymName,
        gymId,
        createdBy,
        modifiedBy), "Filtered Users Returned Successfully");
  }

}
