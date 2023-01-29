package com.bb.focus.db.entity.company;

import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.evaluation.EvaluationSheet;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.entity.interview.InterviewRoom;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;

@Entity(name="company_admins")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="company_admins")
public class CompanyAdmin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "company_admin_id")
  private Long id;

  @Column(length = 50)
  private String userId;

  @Column(length = 64)
  private String pwd;

  @NotNull
  @Column(length = 45)
  private String companyName;

  @NotNull
  private LocalDateTime startDate;

  @NotNull
  private LocalDateTime endDate;

  @NotNull
  @Column(length = 13)
  private String tel;

  @NotNull
  @Column(length = 45)
  private String email;

  @NotNull
  @ColumnDefault("0")
  private boolean isExpired;

  @NotNull
  @Column(length = 10)
  private String name;

  @Column(length = 10)
  private String industry;

  @Column(length = 10)
  private String size;

  @Column(length = 1000)
  private String logoImage;

  @Column(length = 20)
  private String skinColor;

  @NotNull
  @ColumnDefault("2")
  private Byte userRole;

  @OneToMany(targetEntity = com.bb.focus.db.entity.company.Chat.class, mappedBy = "companyAdmin")
  private List<Chat> chatList = new ArrayList<>();

  @OneToMany(targetEntity = com.bb.focus.db.entity.evaluation.EvaluationSheet.class, mappedBy = "companyAdmin")
  private List<EvaluationSheet> evaluationSheetList = new ArrayList<>();

  @OneToMany(targetEntity = com.bb.focus.db.entity.evaluator.Evaluator.class, mappedBy = "companyAdmin")
  private List<Evaluator> evaluatorList = new ArrayList<>();

  @OneToMany(targetEntity = com.bb.focus.db.entity.applicant.Applicant.class, mappedBy = "companyAdmin")
  private List<Applicant> applicantList = new ArrayList<>();

  @OneToMany(targetEntity = com.bb.focus.db.entity.interview.InterviewRoom.class, mappedBy = "companyAdmin")
  private List<InterviewRoom> interviewRoomList = new ArrayList<>();

  //연관관계 메서드
  public void addEvaluator(Evaluator evaluator){
    this.evaluatorList.add(evaluator);
    if(evaluator.getCompanyAdmin() != this){
      evaluator.setCompanyAdmin(this);
    }
  }

  public void addApplicant(Applicant applicant){
    this.applicantList.add(applicant);
    if(applicant.getCompanyAdmin() != this){
      applicant.setCompanyAdmin(this);
    }
  }

  public void addEvaluationSheet(EvaluationSheet evaluationSheet){
    this.evaluationSheetList.add(evaluationSheet);
    if(evaluationSheet.getCompanyAdmin() != this){
      evaluationSheet.setCompanyAdmin(this);
    }
  }

}
