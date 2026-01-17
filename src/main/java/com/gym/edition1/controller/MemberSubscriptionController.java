package com.gym.edition1.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import com.gym.edition1.dto.generic.ApiResponse;
import com.gym.edition1.dto.request.memberSubscription.CreateMemberSubscriptionRequest;
import com.gym.edition1.dto.request.memberSubscription.UpdateMemberSubscriptionRequest;
import com.gym.edition1.model.MemberSubscription;
import com.gym.edition1.service.MemberSubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member-subscriptions")
@RequiredArgsConstructor
public class MemberSubscriptionController {

  private final MemberSubscriptionService memberSubscriptionService;

  @PostMapping
  public ApiResponse<MemberSubscription> createMemberSubscription(
      @RequestBody CreateMemberSubscriptionRequest createMemberSubscriptionRequest) {
    return ApiResponse.created(memberSubscriptionService.createMemberSubscription(createMemberSubscriptionRequest),
        "Member Subscription Created Successfully");
  }

  @GetMapping("/{memberSubscriptionId}")
  public ApiResponse<MemberSubscription> getMemberSubscriptionById(@PathVariable Long memberSubscriptionId) {
    return ApiResponse.success(memberSubscriptionService.getMemberSubscriptionById(memberSubscriptionId),
        "Member Subscription Returned Successfully");
  }

  @PutMapping("/{memberSubscriptionId}")
  public ApiResponse<MemberSubscription> updateMemberSubscription(@PathVariable Long memberSubscriptionId,
      @RequestBody UpdateMemberSubscriptionRequest updateMemberSubscriptionRequest) {
    return ApiResponse.success(
        memberSubscriptionService.updateMemberSubscription(memberSubscriptionId, updateMemberSubscriptionRequest),
        "Member Subscription Updated Successfully");
  }

  @DeleteMapping("/{memberSubscriptionId}")
  public ApiResponse<Void> deleteMemberSubscription(@PathVariable Long memberSubscriptionId) {
    memberSubscriptionService.deleteMemberSubscriptionById(memberSubscriptionId);
    return ApiResponse.noContent("Member Subscription Deleted Successfully");
  }

  @GetMapping
  public ApiResponse<List<MemberSubscription>> getMemberSubscriptionsWithFilters(Long gymId,
      @RequestParam(name = "gymName", required = false) String gymName,
      @RequestParam(name = "memberName", required = false) String memberName,
      @RequestParam(name = "receiptNumber", required = false) String receiptNumber,
      @RequestParam(name = "dateFrom", required = false) LocalDate dateFrom,
      @RequestParam(name = "dateTo", required = false) LocalDate dateTo,
      @RequestParam(name = "createdBy", required = false) Long createdBy,
      @RequestParam(name = "modifiedBy", required = false) Long modifiedBy) {
    return ApiResponse.success(
        memberSubscriptionService.getMemberSubscriptionsWithFilters(gymId, gymName, memberName, receiptNumber, dateFrom,
            dateTo, createdBy, modifiedBy),
        "Filtered Member Subscription Returned Successfully");
  }

}
