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
@Table(name = "Subscription")
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long subscriptionId;

  @Column(name = "subscription_name")
  private String subscriptionName;

  @Column(name = "gym_id")
  private Long gymId;

  @Column(name = "num_of_days")
  private Long numOfDays;

  @Column(name = "num_of_sessions")
  private Long numOfSessions;

  @Column(name = "price")
  private Double price;

  @Column(name = "is_active")
  private String isActive;

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
