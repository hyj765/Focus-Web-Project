package com.bb.focus.api.service;

import com.bb.focus.api.request.CompanyAdminRegisterPostReq;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.repository.CompanyAdminRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("companyAdminService")
public class CompanyAdminServiceImpl implements CompanyAdminService {

  @Autowired
  CompanyAdminRepository companyAdminRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public CompanyAdmin createCompanyAdmin(CompanyAdminRegisterPostReq userRegisterInfo) {
    CompanyAdmin companyAdmin = new CompanyAdmin();
    companyAdmin.setUserId(userRegisterInfo.getUserId());
    companyAdmin.setPwd(passwordEncoder.encode(userRegisterInfo.getPwd()));
    companyAdmin.setCompanyName(userRegisterInfo.getCompanyName());
    companyAdmin.setStartDate(userRegisterInfo.getStartDate());
    companyAdmin.setEndDate(userRegisterInfo.getEndDate());
    companyAdmin.setTel(userRegisterInfo.getTel());
    companyAdmin.setEmail(userRegisterInfo.getEmail());
    companyAdmin.setName(userRegisterInfo.getName());
    companyAdmin.setIndustry(userRegisterInfo.getIndustry());
    companyAdmin.setSize(userRegisterInfo.getSize());
    companyAdmin.setLogoImage(userRegisterInfo.getLogoImage());
    companyAdmin.setSkinColor(userRegisterInfo.getSkinColor());
    companyAdmin.setUserRole(userRegisterInfo.getUserRole());
    return companyAdminRepository.save(companyAdmin);
  }

  @Override
  public CompanyAdmin getCompanyAdminByUserId(String userId) {
    CompanyAdmin companyAdmin = companyAdminRepository.findCompanyAdminByUserId(userId)
        .orElse(new CompanyAdmin());
    return companyAdmin;
  }

  @Override
  public List<CompanyAdmin> getAll() {
    List<CompanyAdmin> companyAdminList = companyAdminRepository.findAll();
    return companyAdminList;
  }

  @Override
  public Long updateCompanyAdminByUserInfo(CompanyAdminRegisterPostReq userUpdateInfo) {
    CompanyAdmin companyAdmin = new CompanyAdmin();
    companyAdmin.setUserId(userUpdateInfo.getUserId());
    companyAdmin.setPwd(passwordEncoder.encode(userUpdateInfo.getPwd()));
    companyAdmin.setCompanyName(userUpdateInfo.getCompanyName());
    companyAdmin.setStartDate(userUpdateInfo.getStartDate());
    companyAdmin.setEndDate(userUpdateInfo.getEndDate());
    companyAdmin.setTel(userUpdateInfo.getTel());
    companyAdmin.setEmail(userUpdateInfo.getEmail());
    companyAdmin.setName(userUpdateInfo.getName());
    companyAdmin.setIndustry(userUpdateInfo.getIndustry());
    companyAdmin.setSize(userUpdateInfo.getSize());
    companyAdmin.setLogoImage(userUpdateInfo.getLogoImage());
    companyAdmin.setSkinColor(userUpdateInfo.getSkinColor());
    companyAdmin.setUserRole(userUpdateInfo.getUserRole());
    Long companyAdminId = companyAdminRepository.updateCompanyAdminByUserId(companyAdmin);
    return companyAdminId;
  }
}
