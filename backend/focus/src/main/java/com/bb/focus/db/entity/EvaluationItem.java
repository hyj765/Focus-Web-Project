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
@Table(name = "evaluation_items")
public class EvaluationItem {

    @Id
    @GeneratedValue
    @Column(name = "evaluation_item_id")
    private Long id;

    private Long evaluation_sheet_id;

    private String scale_content;

    private int score_range;

}