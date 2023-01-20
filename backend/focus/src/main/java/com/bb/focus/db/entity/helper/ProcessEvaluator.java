package com.bb.focus.db.entity.helper;

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
    private Long id;

    private Long processId;

    private Long evaluatorId;
}
