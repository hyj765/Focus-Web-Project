package com.bb.focus.db.entity.evaluation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "evaluation_results")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "evaluation_results")
public class EvaluationResult {

    @Id
    @GeneratedValue
    @Column(name = "evaluation_result_id")
    private Long id;

    private Long evaluationItemId;
    private Long applicantEvaluatorId;

    private String content;
    private int score;
}