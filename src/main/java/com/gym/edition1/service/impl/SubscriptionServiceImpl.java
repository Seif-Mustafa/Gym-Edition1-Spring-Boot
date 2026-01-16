package com.gym.edition1.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gym.edition1.dto.request.subscription.CreateSubscriptionRequest;
import com.gym.edition1.dto.request.subscription.UpdateSubscriptionRequest;
import com.gym.edition1.mapper.SubscriptionMapper;
import com.gym.edition1.model.Subscription;
import com.gym.edition1.repository.SubscriptionRepository;
import com.gym.edition1.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final SubscriptionMapper subscriptionMapper;

  @Override
  public Subscription createSubscription(CreateSubscriptionRequest createSubscriptionRequest) {
    Subscription createdSubscription = subscriptionRepository
        .save(subscriptionMapper.toEntity(createSubscriptionRequest));
    return createdSubscription;
  }

  @Override
  public Subscription getSubscriptionById(Long subscriptionId) {
    Subscription subscription = subscriptionRepository.findById(subscriptionId)
        .orElseThrow(() -> new RuntimeException("Subscription not found"));
    return subscription;
  }

  @Override
  public Subscription updateSubscription(Long subscriptionId, UpdateSubscriptionRequest updateSubscriptionRequest) {
    Subscription existingSubscription = getSubscriptionById(subscriptionId);
    Subscription updatedSubscription = subscriptionRepository
        .save(subscriptionMapper.toEntity(existingSubscription, updateSubscriptionRequest));
    return updatedSubscription;
  }

  @Override
  public void deleteSubscriptionById(Long subscriptionId) {
    Subscription existingSubscription = getSubscriptionById(subscriptionId);
    subscriptionRepository.delete(existingSubscription);
  }

  @Override
  public List<Subscription> getSubscriptionsWithFilters(
      String gymName,
      Long gymId,
      String subscriptionName,
      Long createdBy,
      Long modifiedBy) {
    return subscriptionRepository.getSubscriptionsWithFilters(gymName, gymId, subscriptionName,
        createdBy,
        modifiedBy);
  }

}
