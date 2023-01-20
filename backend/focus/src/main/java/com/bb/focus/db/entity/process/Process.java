package com.bb.focus.db.entity.process;

import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.helper.EvaluatorInterviewRoom;
import com.bb.focus.db.entity.helper.ProcessApplicant;
import com.bb.focus.db.entity.helper.ProcessApplicantPassLog;
import com.bb.focus.db.entity.helper.ProcessEvaluator;
import java.util.ArrayList;
import java.util.List;
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
