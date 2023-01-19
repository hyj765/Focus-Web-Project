package com.bb.focus.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluators")
@Getter
@Setter
@NoArgsConstructor
public class Evaluator {

    @Id
    @GeneratedValue
    @Column(name = "evaluator_id")
    private int id;

    private Long companyAdminId;

    private String userId;
    private String pwd;
    private String name;
    private String code;
    private String department;
    private String rank;
    private String tel;
    private String email;
    private LocalDateTime expireDate;
    private int userRole;
    private String image;   //1:서비스관리자, 2:기업관리자, 3:평가자, 4:지원자
}
