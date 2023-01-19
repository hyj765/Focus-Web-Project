package com.bb.focus.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Process {

    @Id
    @GeneratedValue
    private int processId;

    @ManyToOne
    @JoinColumn(name="company_admin_id")
    private CompanyAdmin companyAdminId;
    private int interviewCount;
    private String name;
    private String startDate;
    private String endDate;
    private int currentStep;
}
