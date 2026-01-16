package com.gym.edition1.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Member")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  @Column(name = "gym_id")
  private Long gymId;

  @Column(name = "name")
  private String name;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "gender")
  private Character gender;

  @Column(name = "age")
  private Integer age;

  @CreatedBy
  @Column(name = "created_by", nullable = false, updatable = false)
  private Long createdBy;

  @CreatedDate
  @Column(name = "created_on", nullable = false, updatable = false)
  private LocalDateTime createdOn;

  @LastModifiedBy
  @Column(name = "modified_by")
  private Long modifiedBy;

  @LastModifiedDate
  @Column(name = "modified_on")
  private LocalDateTime modifiedOn;
}