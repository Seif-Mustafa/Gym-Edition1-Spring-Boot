package com.gym.edition1.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.gym.edition1.dto.request.subscription.CreateSubscriptionRequest;
import com.gym.edition1.dto.request.subscription.UpdateSubscriptionRequest;
import com.gym.edition1.model.Subscription;

@Component
public class SubscriptionMapper {
  public Subscription toEntity(CreateSubscriptionRequest request) {
    Subscription subscription = new Subscription();

    subscription.setSubscriptionName(request.getSubscriptionName());
    subscription.setGymId(request.getGymId());
    subscription.setNumOfDays(request.getNumOfDays());
    subscription.setNumOfSessions(request.getNumOfSessions());
    subscription.setPrice(request.getPrice());
    subscription.setIsActive(request.getIsActive());
    subscription.setCreatedBy(request.getCreatedBy());
    subscription.setCreatedOn(LocalDateTime.now());

    return subscription;
  }

  public Subscription toEntity(
      Subscription existingSubscription,
      UpdateSubscriptionRequest request) {

    if (request.getSubscriptionName() != null) {
      existingSubscription.setSubscriptionName(request.getSubscriptionName());
    }

    if (request.getGymId() != null) {
      existingSubscription.setGymId(request.getGymId());
    }

    if (request.getNumOfDays() != null) {
      existingSubscription.setNumOfDays(request.getNumOfDays());
    }

    if (request.getNumOfSessions() != null) {
      existingSubscription.setNumOfSessions(request.getNumOfSessions());
    }

    if (request.getPrice() != null) {
      existingSubscription.setPrice(request.getPrice());
    }

    if (request.getIsActive() != null) {
      existingSubscription.setIsActive(request.getIsActive());
    }

    existingSubscription.setModifiedBy(request.getModifiedBy());
    existingSubscription.setModifiedOn(LocalDateTime.now());

    return existingSubscription;
  }

}
