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
@Table(name = "evaluation_results")
public class EvaluationResult {

    @Id
    @GeneratedValue
    @Column(name = "evaluation_result_id")
    private Long id;

    private Long evaluation_item_id;

    private Long applicant_evaluator_id;

    private String content;

    private int score;
}