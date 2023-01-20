package com.bb.focus.db.entity.helper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "interview_evaluators")
@Getter
@Setter
//@NoArgsConstructor
@Table(name = "interview_evaluators")
public class InterviewEvaluator {

    @Id
    @GeneratedValue
    @Column(name = "interview_evaluators_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="interview_id")
    private Interview interview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="evaluator_id")
    private Evaluator evaluator;

}
