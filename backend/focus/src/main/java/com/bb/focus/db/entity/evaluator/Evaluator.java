package com.bb.focus.db.entity.evaluator;

import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.helper.ApplicantEvaluator;
import com.bb.focus.db.entity.helper.EvaluatorInterviewRoom;
import com.bb.focus.db.entity.helper.InterviewEvaluator;
import com.bb.focus.db.entity.helper.ProcessEvaluator;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "evaluators")
public class Evaluator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluator_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_admin_id")
    private CompanyAdmin companyAdmin;

    @Column(length = 50)
    private String userId;

    @Column(length = 64)
    private String pwd;

    @NotNull
    @Column(length = 45)
    private String name;

    @NotNull
    @Column(length = 45)
    private String code;

    @Column(length = 45)
    private String department;

    @Column(length = 30)
    private String position;

    @NotNull
    @Column(length = 13)
    private String tel;

    @NotNull
    @Column(length = 45)
    private String email;

    private LocalDateTime expireDate;

    @NotNull
    @ColumnDefault("3")
    private Byte userRole;

    @Column(length = 2090)
    private String image;   //1:서비스관리자, 2:기업관리자, 3:평가자, 4:지원자

    @OneToMany(targetEntity = com.bb.focus.db.entity.helper.ApplicantEvaluator.class, mappedBy = "evaluator")
    private List<ApplicantEvaluator> applicantEvaluatorList = new ArrayList<>();

    @OneToMany(targetEntity = com.bb.focus.db.entity.helper.EvaluatorInterviewRoom.class, mappedBy = "evaluator")
    private List<EvaluatorInterviewRoom> evaluatorInterviewRoomList = new ArrayList<>();

    @OneToMany(targetEntity = com.bb.focus.db.entity.helper.InterviewEvaluator.class, mappedBy = "evaluator")
    private List<InterviewEvaluator> interviewEvaluatorList = new ArrayList<>();

    @OneToMany(targetEntity = com.bb.focus.db.entity.helper.ProcessEvaluator.class, mappedBy = "evaluator")
    private List<ProcessEvaluator> processEvaluatorList = new ArrayList<>();
}
