package com.bb.focus.db.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "applicants")
public class Applicant {

    @Id
    @GeneratedValue
    @Column(name = "applicant_id")
    private Long id;

    private Long application_college_id;

    private Long applicants_univ_id;

    private Long applicants_graduate_id;

    private Long company_admin_id;

    private Long applicants_pass_log_id;

    private String user_id;

    private String pwd;

    private String code;

    private String name;

    private String gender;

    private LocalDateTime birth;

    private String image;

    private String tel;

    private String resume;

    private int pass;

    private String degree;

    private LocalDateTime expire_date;

    private LocalDateTime real_enter_time;

    private LocalDateTime real_exit_time;

    private int user_role;

    private int award_count;

    private int activity_count;

}
