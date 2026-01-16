package com.gym.edition1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gym.edition1.dto.generic.ApiResponse;
import com.gym.edition1.dto.request.subscription.CreateSubscriptionRequest;
import com.gym.edition1.dto.request.subscription.UpdateSubscriptionRequest;
import com.gym.edition1.model.Subscription;
import com.gym.edition1.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  @PostMapping
  public ApiResponse<Subscription> createSubscription(
      @RequestBody CreateSubscriptionRequest createSubscriptionRequest) {
    return ApiResponse.created(subscriptionService.createSubscription(createSubscriptionRequest),
        "Subscription Created Successfully");
  }

  @GetMapping("/{SubscriptionId}")
  public ApiResponse<Subscription> getSubscriptionById(@PathVariable Long SubscriptionId) {
    return ApiResponse.success(subscriptionService.getSubscriptionById(SubscriptionId),
        "Subscription Returned Successfully");
  }

  @PutMapping("/{SubscriptionId}")
  public ApiResponse<Subscription> updateSubscription(@PathVariable Long SubscriptionId,
      @RequestBody UpdateSubscriptionRequest updateSubscriptionRequest) {
    return ApiResponse.success(subscriptionService.updateSubscription(SubscriptionId, updateSubscriptionRequest),
        "Subscription Updated Successfully");
  }

  @DeleteMapping("/{SubscriptionId}")
  public ApiResponse<Subscription> deleteSubscription(@PathVariable Long SubscriptionId) {
    subscriptionService.deleteSubscriptionById(SubscriptionId);
    return ApiResponse.noContent("Subscription Deleted Successfully");
  }

  @GetMapping
  public ApiResponse<List<Subscription>> getSubscriptionsWithFilters(
      @RequestParam(name = "gymName", required = false) String gymName,
      @RequestParam(name = "gymId", required = false) Long gymId,
      @RequestParam(name = "subscriptionName", required = false) String subscriptionName,
      @RequestParam(name = "createdBy", required = false) Long createdBy,
      @RequestParam(name = "modifiedBy", required = false) Long modifiedBy) {
    return ApiResponse.success(subscriptionService.getSubscriptionsWithFilters(gymName, gymId, subscriptionName,
        createdBy,
        modifiedBy),
        "Filtered Subscriptions Returned Successfully");
  }

}
