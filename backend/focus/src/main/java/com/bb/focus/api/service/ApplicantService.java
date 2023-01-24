package com.bb.focus.api.service;

import com.bb.focus.api.request.ApplicantInfoReq;
import com.bb.focus.db.entity.applicant.Applicant;
import java.util.List;

public interface ApplicantService {

  public Long create(Long comapnyAdminId, ApplicantInfoReq applicantInfoReq);

  public void autoAssignAccount(Long id);

  public Long updateApplicantInfo(Long id, ApplicantInfoReq applicantInfoReq);

  public void removeApplicant(Long id);

  public List<Applicant> findAllApplicants(Long companyAdminId);

  public Applicant findApplicant(Long id);

}
