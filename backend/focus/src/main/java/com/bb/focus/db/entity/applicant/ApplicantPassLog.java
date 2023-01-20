package com.bb.focus.db.entity.applicant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name="applicants_pass_log")
@Getter
@Setter
//@NoArgsConstructor
@Table(name="applicants_pass_log")
public class ApplicantPassLog {

    @Id
    @GeneratedValue
    private Long applicantPassLogId;
    private String userId;
    private String code;
    private String name;
    private String email;
    private String processName;
    private String interviewName;
    private int step;


    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    private Date createdAt;

    private int score;




}
