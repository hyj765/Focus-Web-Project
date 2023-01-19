package com.bb.focus.db.entity.interview;

import com.bb.focus.db.entity.process.Process;
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



}
