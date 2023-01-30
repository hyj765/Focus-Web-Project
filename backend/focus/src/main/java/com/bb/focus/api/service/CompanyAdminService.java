package com.bb.focus.api.service;

import com.bb.focus.api.request.CompanyAdminRegisterPostReq;
import com.bb.focus.db.entity.company.CompanyAdmin;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompanyAdminService {
    CompanyAdmin createCompanyAdmin(CompanyAdminRegisterPostReq userRegisterInfo);

    CompanyAdmin getCompanyAdminByUserId(String userId);
    CompanyAdmin getCompanyAdminById(Long id);

    List<CompanyAdmin> getAll();

    Long updateCompanyAdminByUserInfo(CompanyAdminRegisterPostReq userUpdateInfo);
}
