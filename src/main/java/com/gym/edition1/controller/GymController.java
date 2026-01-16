package com.gym.edition1.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.gym.edition1.dto.generic.ApiResponse;
import com.gym.edition1.dto.request.gym.CreateGymRequest;
import com.gym.edition1.dto.request.gym.UpdateGymRequest;
import com.gym.edition1.model.Gym;
import com.gym.edition1.service.GymService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gyms")
@RequiredArgsConstructor
public class GymController {

  private final GymService gymService;

  @PostMapping
  public ApiResponse<Gym> createGym(@RequestBody CreateGymRequest createGymRequest) {
    return ApiResponse.created(gymService.createGym(createGymRequest), "Gym Created Successfully");
  }

  @GetMapping("/{gymId}")
  public ApiResponse<Gym> getGymById(@PathVariable Long gymId) {
    return ApiResponse.success(gymService.getGymById(gymId), "Gym Returned Successfully");
  }

  @PutMapping("/{gymId}")
  public ApiResponse<Gym> updateGym(@PathVariable Long gymId, @RequestBody UpdateGymRequest updateGymRequest) {
    return ApiResponse.success(gymService.updateGym(gymId, updateGymRequest), "Gym Updated Successfully");
  }

  @DeleteMapping("/{gymId}")
  public ApiResponse<Gym> deleteGym(@PathVariable Long gymId) {
    gymService.deleteGymById(gymId);
    return ApiResponse.noContent("Gym Deleted Successfully");
  }

  @GetMapping
  public ApiResponse<List<Gym>> getGymsByFilters(@RequestParam(name = "gymName", required = false) String gymName,
      @RequestParam(name = "createdBy", required = false) Long createdBy,
      @RequestParam(name = "modifiedBy", required = false) Long modifiedBy) {
    return ApiResponse.success(gymService.getGymsByFilters(gymName, createdBy, modifiedBy),
        "Filtered Gyms Returned Successfully");
  }
}