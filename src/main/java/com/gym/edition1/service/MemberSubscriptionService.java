package com.gym.edition1.service;

import java.time.LocalDate;
import java.util.List;

import com.gym.edition1.dto.request.memberSubscription.CreateMemberSubscriptionRequest;
import com.gym.edition1.dto.request.memberSubscription.UpdateMemberSubscriptionRequest;
import com.gym.edition1.model.MemberSubscription;

public interface MemberSubscriptionService {
  MemberSubscription createMemberSubscription(
      CreateMemberSubscriptionRequest createMemberSubscriptionRequest);

  MemberSubscription getMemberSubscriptionById(Long memberSubscriptionId);

  MemberSubscription updateMemberSubscription(Long memberSubscriptionId,
      UpdateMemberSubscriptionRequest updateMemberSubscriptionRequest);

  void deleteMemberSubscriptionById(Long memberSubscriptionId);

  List<MemberSubscription> getMemberSubscriptionsWithFilters(
      Long gymId,
      String gymName,
      String memberName,
      String receiptNumber,
      LocalDate dateFrom,
      LocalDate dateTo,
      Long createdBy,
      Long modifiedBy);
}
