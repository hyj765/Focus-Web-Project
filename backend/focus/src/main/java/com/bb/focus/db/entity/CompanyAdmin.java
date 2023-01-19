package com.bb.focus.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class CompanyAdmin {

    @Id @GeneratedValue
    @Column(name="company_admin_id")
    private Long id;
    private String userId;
    private String pwd;
    private String companyName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String tel;
    private String email;
    private boolean isExpired;
    private String name;
    private String industry;
    private String size;
    private String logoImage;
    private String skinColor;
    private int userRole;






}
