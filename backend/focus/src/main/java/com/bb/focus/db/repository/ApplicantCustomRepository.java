package com.bb.focus.db.repository;

import com.bb.focus.db.entity.applicant.Applicant;
import java.util.List;

public interface ApplicantCustomRepository {

  List<Applicant> findAllApplicantsByCompanyAdminId(Long companyAdminId);

//  Applicant findApplicantByUserId(String userId);

}
