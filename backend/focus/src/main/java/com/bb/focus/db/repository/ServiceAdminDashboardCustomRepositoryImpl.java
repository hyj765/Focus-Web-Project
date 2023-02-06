package com.bb.focus.db.repository;

import com.bb.focus.api.response.ProceedingCompanyRes;
import com.bb.focus.db.entity.company.QCompanyAdmin;
import com.bb.focus.db.entity.process.QProcess;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ServiceAdminDashboardCustomRepositoryImpl implements ServiceAdminDashboardCustomRepository{

  private final JPAQueryFactory jpaQueryFactory;
  QCompanyAdmin qCompanyAdmin = QCompanyAdmin.companyAdmin;
  QProcess qProcess = QProcess.process;

  public List<ProceedingCompanyRes> findAllProceedingCompany() {

    




    return null;
  }
}
