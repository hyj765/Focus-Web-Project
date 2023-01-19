package com.bb.focus.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "process_evaluators")
@Getter
@Setter
@NoArgsConstructor
public class ProcessEvaluator {

    @Id
    @GeneratedValue
    @Column(name = "process_evaluators_id")
    private int id;

    private int processId;
    private int evaluatorId;
}
