package com.gym.edition1.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gym.edition1.dto.request.memberSubscription.CreateMemberSubscriptionRequest;
import com.gym.edition1.dto.request.memberSubscription.UpdateMemberSubscriptionRequest;
import com.gym.edition1.mapper.MemberSubscriptionMapper;
import com.gym.edition1.model.MemberSubscription;
import com.gym.edition1.repository.MemberSubscriptionRepository;
import com.gym.edition1.service.GymService;
import com.gym.edition1.service.MemberService;
import com.gym.edition1.service.MemberSubscriptionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSubscriptionServiceImpl implements MemberSubscriptionService {

  private final MemberSubscriptionRepository memberSubscriptionRepository;
  private final MemberService memberService;
  private final GymService gymService;
  private final MemberSubscriptionMapper memberSubscriptionMapper;

  @Override
  public MemberSubscription createMemberSubscription(
      CreateMemberSubscriptionRequest createMemberSubscriptionRequest) {
    memberService.getMemberById(createMemberSubscriptionRequest.getMemberId());
    gymService.getGymById(createMemberSubscriptionRequest.getGymId());
    MemberSubscription savedMemberSubscription = memberSubscriptionRepository
        .save(memberSubscriptionMapper.toEntity(createMemberSubscriptionRequest));
    return savedMemberSubscription;
  }

  @Override
  public MemberSubscription getMemberSubscriptionById(Long memberSubscriptionId) {
    return memberSubscriptionRepository.findById(memberSubscriptionId)
        .orElseThrow(() -> new RuntimeException("Member Subscription not found"));
  }

  @Override
  public MemberSubscription updateMemberSubscription(Long memberSubscriptionId,
      UpdateMemberSubscriptionRequest updateMemberSubscriptionRequest) {
    MemberSubscription existingMemberSubscription = getMemberSubscriptionById(memberSubscriptionId);
    memberService.getMemberById(updateMemberSubscriptionRequest.getMemberId());
    gymService.getGymById(updateMemberSubscriptionRequest.getGymId());

    memberService.getMemberById(updateMemberSubscriptionRequest.getMemberId());
    return memberSubscriptionRepository
        .save(memberSubscriptionMapper.toEntity(existingMemberSubscription, updateMemberSubscriptionRequest));
  }

  @Override
  public void deleteMemberSubscriptionById(Long memberSubscriptionId) {
    MemberSubscription existingMemberSubscription = getMemberSubscriptionById(memberSubscriptionId);
    memberSubscriptionRepository.delete(existingMemberSubscription);
  }

  @Override
  public List<MemberSubscription> getMemberSubscriptionsWithFilters(
      Long gymId,
      String gymName,
      String memberName,
      String receiptNumber,
      LocalDate dateFrom,
      LocalDate dateTo,
      Long createdBy,
      Long modifiedBy) {
    return memberSubscriptionRepository.getMemberSubscriptionsWithFilters(gymId, gymName, memberName, receiptNumber,
        dateFrom, dateTo, createdBy, modifiedBy);
  }

  @Override
  public MemberSubscription getMemberCurrentSubscription(Long memberId, Long gymId) {
    return memberSubscriptionRepository.getMemberCurrentSubscription(memberId, gymId);
  }
}
