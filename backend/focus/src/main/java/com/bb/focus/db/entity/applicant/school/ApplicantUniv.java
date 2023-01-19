package com.bb.focus.db.entity.applicant.school;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "applicants_univs")
public class ApplicantUniv {

    @Id
    @GeneratedValue
    @Column(name = "applicant_univ_id")
    private Long id;

    private String name;
    private String area;
}
