package com.bb.focus.db.entity.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServiceAdmin {

    @Id
    @GeneratedValue
    @Column(name = "service_admin_id")
    private Long id;

    private String userId;
    private String pwd;
    private String name;
    private String tel;
    private String email;
    private int userRole;   //1:서비스관리자, 2:기업관리자, 3:평가자, 4:지원자
}
