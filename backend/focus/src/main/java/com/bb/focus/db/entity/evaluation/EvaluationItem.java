package com.bb.focus.db.entity.evaluation;

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
@Table(name = "evaluation_items")
public class EvaluationItem {

    @Id
    @GeneratedValue
    @Column(name = "evaluation_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="evaluation_sheet_id")
    private EvaluationSheet evaluationSheet;

    @OneToOne(mappedBy = "evaluationItem", fetch = FetchType.LAZY)
    private EvaluationResult evaluationResult;

    @NotNull
    @Column(length = 500)
    private String scaleContent;

    @NotNull
    private Byte scoreRange;

}