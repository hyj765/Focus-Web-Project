package com.bb.focus.db.entity.applicant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "applicants")
public class Applicant {

    @Id
    @GeneratedValue
    @Column(name = "applicant_id")
    private Long id;
    private Long applicationCollegeId;
    private Long applicantsUnivId;
    private Long applicantsGraduateId;
    private Long companyAdminId;
    private Long applicantsPassLogId;

    private String userId;
    private String pwd;
    private String code;
    private String name;
    private String gender;
    private LocalDateTime birth;
    private String image;
    private String tel;
    private String resume;
    private int pass;
    private String degree;
    private LocalDateTime expireDate;
    private LocalDateTime realEnterTime;
    private LocalDateTime realExitTime;
    private int userRole;
    private int awardCount;
    private int activityCount;

}
