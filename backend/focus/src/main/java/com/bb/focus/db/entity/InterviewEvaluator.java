package com.bb.focus.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "interview_evaluators")
@Getter
@Setter
@NoArgsConstructor
public class InterviewEvaluator {

    @Id
    @GeneratedValue
    @Column(name = "interview_evaluators_id")
    private Long id;

    private Long interviewId;

    private Long evaluatorId;
}
