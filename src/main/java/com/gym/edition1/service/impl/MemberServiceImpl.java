package com.gym.edition1.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.gym.edition1.dto.MemberDto;
import com.gym.edition1.model.Member;
import com.gym.edition1.repository.MemberRepository;

@Service
public class MemberServiceImpl {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<MemberDto> getAllMembers() {
    return memberRepository.findAll().stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  public MemberDto getMemberById(Long id) {
    Member member = memberRepository.findById(id).orElse(null);
    return convertToDto(member);
  }

  public MemberDto createMember(Member member) {
    member.setCreatedOn(LocalDateTime.now());
    Member saved = memberRepository.save(member);
    return convertToDto(saved);
  }

  public MemberDto createMember(MemberDto dto) {
    Member member = convertToEntity(dto);
    member.setCreatedOn(LocalDateTime.now());
    Member saved = memberRepository.save(member);
    return convertToDto(saved);
  }

  public MemberDto updateMember(Long id, Member member) {
    Member existing = memberRepository.findById(id).orElse(null);
    if (existing != null) {
      existing.setName(member.getName());
      existing.setGymId(member.getGymId());
      existing.setPhoneNumber(member.getPhoneNumber());
      existing.setGender(member.getGender());
      existing.setAge(member.getAge());
      existing.setModifiedOn(LocalDateTime.now());
      existing.setModifiedBy(member.getModifiedBy());
      Member updated = memberRepository.save(existing);
      return convertToDto(updated);
    }
    return null;
  }

  public MemberDto updateMember(Long id, MemberDto dto) {
    Member existing = memberRepository.findById(id).orElse(null);
    if (existing != null) {
      existing.setName(dto.getName());
      existing.setGymId(dto.getGymId());
      existing.setPhoneNumber(dto.getPhoneNumber());
      existing.setGender(dto.getGender());
      existing.setAge(dto.getAge());
      existing.setModifiedOn(LocalDateTime.now());
        existing.setModifiedBy(dto.getModifiedBy());

      Member updated = memberRepository.save(existing);
      return convertToDto(updated);
    }
    return null;
  }

  public void deleteMember(Long id) {
    memberRepository.deleteById(id);
  }

  private MemberDto convertToDto(Member member) {
    if (member == null)
      return null;
    
    MemberDto dto = modelMapper.map(member, MemberDto.class);
    
    // Map audit fields
    dto.setCreatedBy(member.getCreatedBy());
    dto.setCreatedOn(member.getCreatedOn());
    dto.setModifiedBy(member.getModifiedBy());
    dto.setModifiedOn(member.getModifiedOn());
    
    return dto;
  }

  private Member convertToEntity(MemberDto dto) {
    if (dto == null)
      return null;
    
    Member member = modelMapper.map(dto, Member.class);
    
    // Set audit fields manually if user is provided
      member.setCreatedBy(dto.getCreatedBy());
      member.setModifiedBy(dto.getModifiedBy());
    
    return member;
  }
}