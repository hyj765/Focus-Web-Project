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
@Table(name = "applicants_colleges")
public class ApplicantCollege {

    @Id
    @GeneratedValue
    @Column(name = "applicant_college_id")
    private Long id;

    private String name;
    private String area;
}
