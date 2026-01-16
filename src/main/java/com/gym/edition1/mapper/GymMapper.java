package com.gym.edition1.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.gym.edition1.dto.request.gym.CreateGymRequest;
import com.gym.edition1.dto.request.gym.UpdateGymRequest;
import com.gym.edition1.model.Gym;

@Component
public class GymMapper {
  public Gym toEntity(CreateGymRequest createGymRequest) {
    Gym gym = new Gym();
    gym.setGymName(createGymRequest.getGymName());
    gym.setCreatedBy(createGymRequest.getCreatedBy());
    gym.setCreatedOn(LocalDateTime.now());
    return gym;
  }

  public Gym toEntity(Gym existingGym, UpdateGymRequest updateGymRequest) {
    if (updateGymRequest.getGymName() != null) {
      existingGym.setGymName(updateGymRequest.getGymName());
    }

    existingGym.setModifiedBy(updateGymRequest.getModifiedBy());
    existingGym.setModifiedOn(LocalDateTime.now());
  
    return existingGym;
  }
}
