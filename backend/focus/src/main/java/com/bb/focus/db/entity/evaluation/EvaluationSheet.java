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
@Table(name = "evaluation_sheets")
public class EvaluationSheet {

    @Id
    @GeneratedValue
    @Column(name = "evaluation_sheet_id")
    private Long id;

    private Long companyAdminId;

    private String name;

}