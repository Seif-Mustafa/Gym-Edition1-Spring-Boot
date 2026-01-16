package com.gym.edition1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.gym.edition1.dto.MemberDto;
import com.gym.edition1.service.impl.MemberServiceImpl;

@RestController
@RequestMapping("/members")
public class MemberController {

  @Autowired
  private MemberServiceImpl memberService;

  @GetMapping
  public ResponseEntity<List<MemberDto>> getAllMembers() {
    return ResponseEntity.ok(memberService.getAllMembers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
    return ResponseEntity.ok(memberService.getMemberById(id));
  }

  @PostMapping
  public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
    return ResponseEntity.ok(memberService.createMember(memberDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<MemberDto> updateMember(@PathVariable Long id, @RequestBody MemberDto memberDto) {
    return ResponseEntity.ok(memberService.updateMember(id, memberDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
    memberService.deleteMember(id);
    return ResponseEntity.noContent().build();
  }
}
