package com.bb.focus.db.entity;

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

    private Long evaluator_id;

    private Long applicant_id;

    private Long interview_id;

    private Long evaluation_sheet_id;

    private String memo;

    private int score;

}