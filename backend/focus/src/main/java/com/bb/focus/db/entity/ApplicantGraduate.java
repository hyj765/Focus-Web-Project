package com.bb.focus.db.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "applicants_graduates")
public class ApplicantGraduate {

    @Id
    @GeneratedValue
    @Column(name = "applicant_graduate_id")
    private Long id;

    private String name;

    private String area;
}
