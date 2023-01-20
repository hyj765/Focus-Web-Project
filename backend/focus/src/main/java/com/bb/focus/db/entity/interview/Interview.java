package com.bb.focus.db.entity.interview;

import com.bb.focus.db.entity.evaluation.EvaluationSheet;
import com.bb.focus.db.entity.helper.ApplicantEvaluator;
import com.bb.focus.db.entity.helper.InterviewEvaluator;
import com.bb.focus.db.entity.helper.InteviewApplicantPassLog;
import com.bb.focus.db.entity.process.Process;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Interview {
    @Id
    @GeneratedValue
    private Long interviewId;

    @ManyToOne
    @JoinColumn(name="evaluation_sheet_id")
    private  int evaluationSheetsId;

    @ManyToOne
    @JoinColumn(name="process_id")
    private Process processId;
    private int step;
    private String name;
    private String startDate;
    private String endDate;

    @OneToMany(mappedBy = "interview")
    private List<ApplicantEvaluator> applicantEvaluatorList = new ArrayList<>();

    @OneToMany(mappedBy = "interview")
    private List<Room> roomList = new ArrayList<>();

    @OneToMany(mappedBy = "interview")
    private List<InteviewApplicantPassLog> InteviewApplicantPassLogList = new ArrayList<>();

    @OneToMany(mappedBy = "interview")
    private List<InterviewEvaluator> interviewEvaluatorList = new ArrayList<>();
}
