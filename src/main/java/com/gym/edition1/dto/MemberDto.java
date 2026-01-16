package com.gym.edition1.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long member_id;
    private Long gymId;
    private String name;
    private String phoneNumber;
    private Character gender;
    private Integer age;

    // Audit fields (read-only, populated by system)
    private Long createdBy;
    private java.time.LocalDateTime createdOn;
    private Long modifiedBy;
    private java.time.LocalDateTime modifiedOn;
}