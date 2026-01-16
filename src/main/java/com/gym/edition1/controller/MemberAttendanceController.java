package com.gym.edition1.controller;

import com.gym.edition1.dto.MemberAttendanceDto;
import com.gym.edition1.dto.generic.ApiResponse;
import com.gym.edition1.dto.request.MemberAttendRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.gym.edition1.model.MemberAttendance;
import com.gym.edition1.service.impl.MemberAttendanceServiceImpl;

@RestController
@RequestMapping("/attendances")
public class MemberAttendanceController {

    @Autowired
    private MemberAttendanceServiceImpl memberAttendanceService;

    @PostMapping
    public ApiResponse<MemberAttendance> createAttendance(@RequestBody MemberAttendRequest memberAttendRequest) {
        MemberAttendance attendance = memberAttendanceService.createAttendance(memberAttendRequest);
        return ApiResponse.created(attendance, "Member Attendance Created Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberAttendanceDto> getAttendanceById(@PathVariable Long id) {
        MemberAttendanceDto attendance = memberAttendanceService.getAttendanceById(id);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping
    public ResponseEntity<List<MemberAttendanceDto>> getAllAttendances() {
        List<MemberAttendanceDto> attendances = memberAttendanceService.getAllAttendances();
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<MemberAttendanceDto>> getAttendancesByMemberId(@PathVariable Long memberId) {
        List<MemberAttendanceDto> attendances = memberAttendanceService.getAttendancesByMemberId(memberId);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/gym/{gymId}")
    public ResponseEntity<List<MemberAttendanceDto>> getAttendancesByGymId(@PathVariable Long gymId) {
        List<MemberAttendanceDto> attendances = memberAttendanceService.getAttendancesByGymId(gymId);
        return ResponseEntity.ok(attendances);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        memberAttendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance record deleted successfully");
    }
}