package com.gym.edition1.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gym.edition1.dto.request.gym.CreateGymRequest;
import com.gym.edition1.dto.request.gym.UpdateGymRequest;
import com.gym.edition1.mapper.GymMapper;
import com.gym.edition1.model.Gym;
import com.gym.edition1.repository.GymRepository;
import com.gym.edition1.service.GymService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {

  private final GymRepository gymRepository;
  private final GymMapper gymMapper;

  @Override
  public Gym createGym(CreateGymRequest createGymRequest) {
    return gymRepository.save(gymMapper.toEntity(createGymRequest));
  }

  @Override
  public Gym getGymById(Long gymId) {
    return gymRepository.findById(gymId).orElseThrow(() -> new RuntimeException("Gym not found"));
  }

  @Override
  public Gym updateGym(Long gymId, UpdateGymRequest updateGymRequest) {
    Gym existingGym = getGymById(gymId);

    Gym updatedGym = gymRepository.save(gymMapper.toEntity(existingGym, updateGymRequest));
    return updatedGym;
  }

  @Override
  public void deleteGymById(Long gymId) {
    Gym existingGym = getGymById(gymId);
    gymRepository.delete(existingGym);
  }

  @Override
  public List<Gym> getGymsByFilters(String gymName, Long createdBy, Long modifiedBy){
    return gymRepository.getGymsByFilters(gymName, createdBy, modifiedBy);
  }



}