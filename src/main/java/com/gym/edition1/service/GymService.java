package com.gym.edition1.service;

import java.util.List;

import com.gym.edition1.dto.request.gym.CreateGymRequest;
import com.gym.edition1.dto.request.gym.UpdateGymRequest;
import com.gym.edition1.model.Gym;

public interface GymService {
  Gym createGym(CreateGymRequest createGymRequest);

  Gym getGymById(Long gymId);

  Gym updateGym(Long gymId, UpdateGymRequest updateGymRequest);

  void deleteGymById(Long gymId);

  List<Gym> getGymsByFilters(String gymName, Long createdBy, Long modifiedBy);

}
