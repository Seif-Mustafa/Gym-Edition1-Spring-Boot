package com.gym.edition1.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.gym.edition1.dto.generic.ApiResponse;
import com.gym.edition1.dto.request.member.CreateMemberRequest;
import com.gym.edition1.dto.request.member.UpdateMemberRequest;
import com.gym.edition1.model.Member;
import com.gym.edition1.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping
  public ApiResponse<Member> createMember(@RequestBody CreateMemberRequest createMemberRequest) {
    return ApiResponse.created(memberService.createMember(createMemberRequest), "Member Created Successfully");
  }

  @GetMapping("/{memberId}")
  public ApiResponse<Member> getMemberById(@PathVariable Long memberId) {
    return ApiResponse.success(memberService.getMemberById(memberId), "Member Returned Successfully");
  }

  @PutMapping("/{memberId}")
  public ApiResponse<Member> updateMember(@PathVariable Long memberId,
      @RequestBody UpdateMemberRequest updateMemberRequest) {
    return ApiResponse.success(memberService.updateMember(memberId, updateMemberRequest),
        "Member Updated Successfully");
  }

  @DeleteMapping("/{memberId}")
  public ApiResponse<Void> deleteMember(@PathVariable Long memberId) {
    memberService.deleteMemberById(memberId);
    return ApiResponse.noContent("Member Deleted Successfully");
  }

  @GetMapping
  public ApiResponse<List<Member>> getMembersWithFilters(
      @RequestParam(name = "gymName", required = false) String gymName,
      @RequestParam(name = "gymId", required = false) Long gymId,
      @RequestParam(name = "memberName", required = false) String memberName,
      @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
      @RequestParam(name = "gender", required = false) String gender,
      @RequestParam(name = "age", required = false) Long age,
      @RequestParam(name = "isDeleted", required = false) String isDeleted,
      @RequestParam(name = "createdBy", required = false) Long createdBy,
      @RequestParam(name = "modifiedBy", required = false) Long modifiedBy) {
    return ApiResponse.success(memberService.getMembersWithFilters(gymName, gymId, memberName, phoneNumber, gender, age,
        isDeleted, createdBy, modifiedBy), "Filtered Members Returned Successfully");
  }

}
