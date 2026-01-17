package com.gym.edition1.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.gym.edition1.dto.request.memberSubscription.CreateMemberSubscriptionRequest;
import com.gym.edition1.dto.request.memberSubscription.UpdateMemberSubscriptionRequest;
import com.gym.edition1.model.MemberSubscription;

@Component
public class MemberSubscriptionMapper {
  public MemberSubscription toEntity(CreateMemberSubscriptionRequest request) {
    MemberSubscription memberSubscription = new MemberSubscription();

    memberSubscription.setMemberId(request.getMemberId());
    memberSubscription.setGymId(request.getGymId());
    memberSubscription.setDateFrom(request.getDateFrom());
    memberSubscription.setDateTo(request.getDateTo());
    memberSubscription.setNumOfSessions(request.getNumOfSessions());
    memberSubscription.setPaidAmount(request.getPaidAmount());
    memberSubscription.setReceiptNumber(request.getReceiptNumber());

    memberSubscription.setCreatedBy(request.getCreatedBy());
    memberSubscription.setCreatedOn(LocalDateTime.now());

    return memberSubscription;
  }

  public MemberSubscription toEntity(
      MemberSubscription existing,
      UpdateMemberSubscriptionRequest request) {

    if (request.getMemberId() != null) {
      existing.setMemberId(request.getMemberId());
    }
    if (request.getGymId() != null) {
      existing.setGymId(request.getGymId());
    }

    if (request.getDateFrom() != null) {
      existing.setDateFrom(request.getDateFrom());
    }

    if (request.getDateTo() != null) {
      existing.setDateTo(request.getDateTo());
    }

    if (request.getNumOfSessions() != null) {
      existing.setNumOfSessions(request.getNumOfSessions());
    }

    if (request.getPaidAmount() != null) {
      existing.setPaidAmount(request.getPaidAmount());
    }

    if (request.getReceiptNumber() != null) {
      existing.setReceiptNumber(request.getReceiptNumber());
    }

    existing.setModifiedBy(request.getModifiedBy()); // rename to modifiedBy
    existing.setModifiedOn(LocalDateTime.now());

    return existing;
  }

}
