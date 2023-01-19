package com.bb.focus.db.entity.process;

import com.bb.focus.db.entity.applicant.ApplicantPassLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="process_applicants_pass_log")
public class ProcessApplicantPassLog
{
    @Id
    @GeneratedValue
    @Column(name="process_applicants_pass_log")
    private int processPassLogId;

    @ManyToOne
    @JoinColumn(name="process_id")
    private Process processId;

    @ManyToOne
    @JoinColumn(name="applicants_pass_log_id")
    private ApplicantPassLog applicantPassLogId;

}
