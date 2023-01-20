package com.bb.focus.db.entity.helper;

import com.bb.focus.db.entity.applicant.ApplicantPassLog;
import com.bb.focus.db.entity.interview.Interview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="interview_applicants_pass_log")
public class InteviewApplicantPassLog {

    @Id
    @GeneratedValue
    @Column(name="interview_applicants_pass_log_id")
    private Long interviewApplicantPass;

    @ManyToOne
    @JoinColumn(name="interview_id")
    private Interview interview;

    @ManyToOne
    @JoinColumn(name="applicants_pass_log_id")
    private ApplicantPassLog applicantPassLog;



}
