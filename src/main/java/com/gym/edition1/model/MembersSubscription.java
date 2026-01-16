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
@Table(name = "members_subscription")
public class MembersSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mem_sub_id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "date_from")
    private java.time.LocalDate dateFrom;

    @Column(name = "date_to")
    private java.time.LocalDate dateTo;

    @Column(name = "num_of_sessions")
    private Integer numOfSessions;

    @Column(name = "paid_amount")
    private Double paidAmount;

    @Column(name = "receipt_number")
    private String receiptNumber;
    
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