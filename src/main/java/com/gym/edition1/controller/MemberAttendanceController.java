package com.gym.edition1.controller;

import com.gym.edition1.dto.generic.ApiResponse;
import com.gym.edition1.dto.request.memberattendance.CreateMemberAttendRequest;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import com.gym.edition1.model.MemberAttendance;
import com.gym.edition1.service.MemberAttendanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
public class MemberAttendanceController {

  private final MemberAttendanceService memberAttendanceService;

  @PostMapping
  public ApiResponse<MemberAttendance> createAttendance(
      @RequestBody CreateMemberAttendRequest createMemberAttendRequest) {
    MemberAttendance attendance = memberAttendanceService.createAttendance(createMemberAttendRequest);
    return ApiResponse.created(attendance, "Member Attendance Created Successfully");
  }

  @GetMapping
  public ApiResponse<List<MemberAttendance>> getMemberAttendanceWithFilters(
      @RequestParam(name = "memberId", required = false) Long memberId,
      @RequestParam(name = "memberName", required = false) String memberName,
      @RequestParam(name = "gymId", required = false) Long gymId,
      @RequestParam(name = "gymName", required = false) String gymName,
      @RequestParam(name = "attendType", required = false) String attendType,
      @RequestParam(name = "attendDate", required = false) LocalDate attendDate,
      @RequestParam(name = "createdBy", required = false) Long createdBy,
      @RequestParam(name = "modifiedBy", required = false) Long modifiedBy) {
    return ApiResponse.success(memberAttendanceService.getMemberAttendanceWithFilters(memberId, memberName, gymId,
        gymName, attendType, attendDate, createdBy, modifiedBy), "Filtered Member Attendance Returned Successfully");
  }

}