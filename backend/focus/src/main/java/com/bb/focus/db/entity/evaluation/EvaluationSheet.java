package com.bb.focus.db.entity.evaluation;

import com.bb.focus.db.entity.company.CompanyAdmin;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_admin_id")
    private CompanyAdmin companyAdmin;

    @NotNull
    @Column(length = 30)
    private String name;

    @OneToMany(targetEntity = com.bb.focus.db.entity.evaluation.EvaluationItem.class, mappedBy = "evaluationSheet")
    private List<EvaluationItem> evaluationItemList = new ArrayList<>();

}