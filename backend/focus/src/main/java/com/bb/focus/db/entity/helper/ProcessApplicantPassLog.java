package com.bb.focus.db.entity.helper;

import com.bb.focus.db.entity.applicant.ApplicantPassLog;
import com.bb.focus.db.entity.process.Process;
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
    private int processPassLog;

    @ManyToOne
    @JoinColumn(name="process_id")
    private Process process;

    @ManyToOne
    @JoinColumn(name="applicants_pass_log_id")
    private ApplicantPassLog applicantPassLog;

}
