package com.bb.focus.db.entity.helper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "applicants_evaluators")
public class ApplicantEvaluator {

    @Id
    @GeneratedValue
    @Column(name="applicant_evaluator_id")
    private Long id;

    private Long evaluatorId;
    private Long applicantId;
    private Long interviewId;
    private Long evaluationSheetId;

    private String memo;
    private int score;

}