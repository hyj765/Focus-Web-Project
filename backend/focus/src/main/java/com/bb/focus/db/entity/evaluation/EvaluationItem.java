package com.bb.focus.db.entity.evaluation;

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

    private Long evaluationSheetId;

    private String scaleContent;

    private int scoreRange;

}