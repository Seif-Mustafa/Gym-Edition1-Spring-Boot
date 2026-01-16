package com.gym.edition1.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.edition1.dto.MemberSubscriptionDto;
import com.gym.edition1.model.Member;
import com.gym.edition1.model.MembersSubscription;
import com.gym.edition1.repository.MemberRepository;
import com.gym.edition1.repository.MembersSubscriptionRepository;

@Service
public class MemberSubscriptionServiceImpl {

  @Autowired
  private MembersSubscriptionRepository memberSubscriptionRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private MemberRepository memberRepository;
  // Get all member-subscription records
  public List<MemberSubscriptionDto> getAllMemberSubscriptions() {
    // Log.info("Fetching all member subscriptions");
    return memberSubscriptionRepository.findAllWithMember().stream()
        .map(this::mapToDtoWithMemberName)
        .collect(Collectors.toList());
  }

  // Get by ID
  public MemberSubscriptionDto getMemberSubscriptionById(Long id) {
    MembersSubscription entity = memberSubscriptionRepository.findByIdWithMember(id);
    return mapToDtoWithMemberName(entity);
  }

  public List<MemberSubscriptionDto> getMembersSubscriptionsByGymId(Long gymId) {
    List<MembersSubscription> membersSubscriptions = memberSubscriptionRepository.findByGymIdWithMember(gymId);
    return membersSubscriptions.stream()
        .map(this::mapToDtoWithMemberName)
        .collect(Collectors.toList());
  }

  // Create new record
  public MemberSubscriptionDto createMemberSubscription(MemberSubscriptionDto dto) {
    MembersSubscription entity = convertToEntity(dto);
    entity.setCreatedOn(LocalDateTime.now());
    MembersSubscription saved = memberSubscriptionRepository.save(entity);

    
    return mapToDtoWithMemberName(saved);
  }

  // Update existing record
  public MemberSubscriptionDto updateMemberSubscription(Long id, MemberSubscriptionDto dto) {
    MembersSubscription existing = memberSubscriptionRepository.findById(id).orElse(null);
    if (existing != null) {
      existing.setDateFrom(dto.getDateFrom());
      existing.setDateTo(dto.getDateTo());
      existing.setModifiedOn(LocalDateTime.now());
        existing.setModifiedBy(dto.getModifiedBy());
      
      // Update Member reference if memberId is provided
      if (dto.getMemberId() != null) {
        Member member = memberRepository.findById(dto.getMemberId()).orElse(null);
        if (member == null) {
          throw new IllegalArgumentException("Member with ID " + dto.getMemberId() + " not found");
        }
        existing.setMember(member);
      }

      MembersSubscription updated = memberSubscriptionRepository.save(existing);
      return mapToDtoWithMemberName(updated);
    }
    return null;
  }

  // Delete record
  public void deleteMemberSubscription(Long id) {
    memberSubscriptionRepository.deleteById(id);
  }

  // Helper: Convert Entity → DTO
  private MemberSubscriptionDto convertToDto(MembersSubscription entity) {
    if (entity == null)
      return null;
    
    MemberSubscriptionDto dto = modelMapper.map(entity, MemberSubscriptionDto.class);
    // Manually map the ID field since field names don't match
    dto.setMemSubId(entity.getMem_sub_id());
    

    
    return dto;
  }

  // Helper: Convert DTO → Entity
  private MembersSubscription convertToEntity(MemberSubscriptionDto dto) {
    if (dto == null)
      return null;
    
    MembersSubscription entity = new MembersSubscription();
    // Don't set ID for new entities (let JPA generate it)
    // Only set ID if it's provided (for updates)
    if (dto.getMemSubId() != null) {
      entity.setMem_sub_id(dto.getMemSubId());
    }
    entity.setDateFrom(dto.getDateFrom());
    entity.setDateTo(dto.getDateTo());
    

    entity.setPaidAmount(dto.getPaidAmount());
    entity.setReceiptNumber(dto.getReceiptNumber());
    // Set audit fields manually if user is provided
      entity.setCreatedBy(dto.getCreatedBy());
      entity.setModifiedBy(dto.getModifiedBy());
    
    // Set Member reference if memberId is provided
    if (dto.getMemberId() != null) {
      Member member = memberRepository.findById(dto.getMemberId()).orElse(null);
      if (member == null) {
        throw new IllegalArgumentException("Member with ID " + dto.getMemberId() + " not found");
      }
      entity.setMember(member);
    }
    
    return entity;
  }

  private MemberSubscriptionDto mapToDtoWithMemberName(MembersSubscription entity) {
    if (entity == null) {
      return null;
    }
    
    MemberSubscriptionDto dto = convertToDto(entity);

    // Set member information from the loaded Member entity
    if (entity.getMember() != null) {
      dto.setMemberId(entity.getMember().getMemberId());
      dto.setMemberName(entity.getMember().getName());
    }

    // Map audit fields
    dto.setCreatedBy(entity.getCreatedBy());
    dto.setCreatedOn(entity.getCreatedOn());
    dto.setModifiedBy(entity.getModifiedBy());
    dto.setModifiedOn(entity.getModifiedOn());

    return dto;
  }
}
