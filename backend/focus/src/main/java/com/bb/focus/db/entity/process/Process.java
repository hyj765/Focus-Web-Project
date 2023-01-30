package com.bb.focus.db.entity.process;

import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.helper.ProcessApplicantPassLog;
import com.bb.focus.db.entity.helper.ProcessEvaluator;
import com.bb.focus.db.entity.interview.Interview;
import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Process {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="process_id")
  private Long id;

    @ManyToOne
    @JoinColumn(name="company_admin_id")
    private CompanyAdmin companyAdmin;

    @NotNull
    private Byte interviewCount;

    @NotNull
    @Column(length = 64)
    private String name;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @NotNull
    @ColumnDefault("1")
    private Byte currentStep;

    @OneToMany(targetEntity = com.bb.focus.db.entity.interview.Interview.class, mappedBy = "process")
    private List<Interview> interviewList = new ArrayList<>();


    @OneToMany(targetEntity = com.bb.focus.db.entity.helper.ProcessApplicantPassLog.class, mappedBy = "process")
    private List<ProcessApplicantPassLog> processApplicantPassLogList = new ArrayList<>();

    @OneToMany(targetEntity = com.bb.focus.db.entity.helper.ProcessEvaluator.class, mappedBy = "process")
    private  List<ProcessEvaluator> processEvaluatorList = new ArrayList<>();

    @OneToMany(targetEntity = com.bb.focus.db.entity.applicant.Applicant.class, mappedBy = "process")
    private List<Applicant> applicantList = new ArrayList<>();
}
