package com.bb.focus.db.entity.evaluation;

import com.bb.focus.db.entity.helper.ApplicantEvaluator;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "evaluation_results")
public class EvaluationResult {

    @Id
    @GeneratedValue
    @Column(name = "evaluation_result_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="evaluation_item_id")
    private EvaluationItem evaluationItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="applicant_evaluator_id")
    private ApplicantEvaluator applicantEvaluator;

    @NotNull
    @Column(length = 1000)
    private String content;

    private Byte score;
}