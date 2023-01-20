package com.bb.focus.db.entity.evaluation;

import com.bb.focus.db.entity.company.CompanyAdmin;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "evaluation_sheets")
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