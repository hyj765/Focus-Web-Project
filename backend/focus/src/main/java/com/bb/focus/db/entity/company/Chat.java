package com.bb.focus.db.entity.company;

import com.bb.focus.db.entity.applicant.Applicant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="chatting")
public class Chat {

    @Id
    @GeneratedValue
    private long chattingId;

    @OneToOne
    @JoinColumn(name="company_admin_id")
    private CompanyAdmin companyAdminId;

    @OneToOne
    @JoinColumn(name="applicant_id")
    private Applicant applicantId;



}
