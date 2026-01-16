package com.gym.edition1.service;

import java.util.List;

import com.gym.edition1.dto.request.subscription.CreateSubscriptionRequest;
import com.gym.edition1.dto.request.subscription.UpdateSubscriptionRequest;
import com.gym.edition1.model.Subscription;

public interface SubscriptionService {
  Subscription createSubscription(CreateSubscriptionRequest createSubscriptionRequest);

  Subscription getSubscriptionById(Long subscriptionId);

  Subscription updateSubscription(Long subscriptionId, UpdateSubscriptionRequest updateSubscriptionRequest);

  void deleteSubscriptionById(Long subscriptionId);

  List<Subscription> getSubscriptionsWithFilters(
      String gymName,
      Long gymId,
      String subscriptionName,
      Long createdBy,
      Long modifiedBy);

}
