package com.bb.focus.db.repository;

import com.bb.focus.api.response.ProceedingCompanyRes;
import java.util.List;
import org.springframework.stereotype.Repository;

public interface ServiceAdminDashboardCustomRepository {

  public List<ProceedingCompanyRes> findAllProceedingCompany();
}
