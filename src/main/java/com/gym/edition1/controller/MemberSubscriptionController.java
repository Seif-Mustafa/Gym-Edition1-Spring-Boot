package com.gym.edition1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.gym.edition1.dto.MemberSubscriptionDto;
import com.gym.edition1.service.impl.MemberSubscriptionServiceImpl;
import com.gym.edition1.util.GenericResponse;

@RestController
@RequestMapping("/member-subscriptions")
public class MemberSubscriptionController {

  @Autowired
  private MemberSubscriptionServiceImpl memberSubscriptionService;

  @GetMapping
  public ResponseEntity<GenericResponse<List<MemberSubscriptionDto>>> getAllMemberSubscriptions() {
    return ResponseEntity.ok(
        GenericResponse.success(memberSubscriptionService.getAllMemberSubscriptions(), "Data returned successfully"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<GenericResponse<MemberSubscriptionDto>> getMemberSubscriptionById(@PathVariable Long id) {
    return ResponseEntity.ok(
        GenericResponse.success(memberSubscriptionService.getMemberSubscriptionById(id), "Data returned successfully"));
  }

  // Add this method to SubscriptionController.java
  @GetMapping("/gym/{gymId}")
  public ResponseEntity<GenericResponse<List<MemberSubscriptionDto>>> getSubscriptionsByGymId(
      @PathVariable Long gymId) {
    return ResponseEntity.ok(GenericResponse.success(memberSubscriptionService.getMembersSubscriptionsByGymId(gymId),
        "Data returned successfully"));
  }

  @PostMapping
  public ResponseEntity<GenericResponse<MemberSubscriptionDto>> createMemberSubscription(
      @RequestBody MemberSubscriptionDto memberSubscriptionDto) {
    return ResponseEntity
        .ok(GenericResponse.created(memberSubscriptionService.createMemberSubscription(memberSubscriptionDto),
            "Member subscription created successfully"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<GenericResponse<MemberSubscriptionDto>> updateMemberSubscription(@PathVariable Long id,
      @RequestBody MemberSubscriptionDto memberSubscriptionDto) {
    return ResponseEntity
        .ok(GenericResponse.updated(memberSubscriptionService.updateMemberSubscription(id, memberSubscriptionDto),
            "Member subscription updated successfully"));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GenericResponse<Void>> deleteMemberSubscription(@PathVariable Long id) {
    memberSubscriptionService.deleteMemberSubscription(id);
    return ResponseEntity.ok(GenericResponse.success(null, "Member subscription deleted successfully"));
  }
}
