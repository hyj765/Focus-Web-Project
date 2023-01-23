package com.bb.focus.api.service;

import com.bb.focus.api.request.CompanyAdminRegisterPostReq;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.repository.CompanyAdminRepository;
import com.bb.focus.db.repository.CompanyAdminRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("companyAdminService")
public class CompanyAdminServiceImpl implements CompanyAdminService {
    @Autowired
    CompanyAdminRepository companyAdminRepository;

    @Autowired
    CompanyAdminRepositorySupport companyAdminRepositorySupport;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public CompanyAdmin createCompanyAdmin(CompanyAdminRegisterPostReq userRegisterInfo) {
        CompanyAdmin companyAdmin = new CompanyAdmin();
        companyAdmin.setUserId(userRegisterInfo.getUserId());
        companyAdmin.setPwd(passwordEncoder.encode(userRegisterInfo.getPwd()));
        return companyAdminRepository.save(companyAdmin);
    }

    @Override
    public CompanyAdmin getCompanyAdminByUserId(String userId) {
        CompanyAdmin companyAdmin = companyAdminRepositorySupport.findCompanyAdminByUserId(userId).orElse(new CompanyAdmin());
        return companyAdmin;
    }
}
