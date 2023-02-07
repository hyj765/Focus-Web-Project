package com.bb.focus.api.service;

import com.bb.focus.api.request.CompanyAdminRegisterPostReq;
import com.bb.focus.common.util.EncryptionUtils;
import com.bb.focus.config.SecurityConfig;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.repository.CompanyAdminRepository;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyAdminService")
public class CompanyAdminServiceImpl implements CompanyAdminService {

  @Autowired
  CompanyAdminRepository companyAdminRepository;

  @Override
  public CompanyAdmin createCompanyAdmin(CompanyAdminRegisterPostReq userRegisterInfo) {
    CompanyAdmin companyAdmin = new CompanyAdmin();

    String userId =
        userRegisterInfo.getCompanyName() + "CA" + userRegisterInfo.getStartDate().toLocalDate()
            .format(
                DateTimeFormatter.ofPattern("yyyyMMdd"));
    String pwd = getRandomString();
//    String pwd = EncryptionUtils.encryptSHA256(getRandomString());

    companyAdmin.setUserId(userId);
    companyAdmin.setPwd(pwd);
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
    CompanyAdmin companyAdmin = companyAdminRepository.findCompanyAdminByUserId(userId);
    return companyAdmin;
  }

  @Override
  public CompanyAdmin getCompanyAdminById(Long id) {
    CompanyAdmin companyAdmin = companyAdminRepository.findCompanyAdminById(id);
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

  @Override
  public List<CompanyAdmin> getAllContainsToday() {
    return companyAdminRepository.findAllContainsToday();
  }

  private String getRandomString() {
    int leftLimit = 48;     //숫자 0
    int rightLimit = 122;   //영문자 z
    int targetStringLength = 10;    //10글자로 생성
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
//        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

    return generatedString;
  }

}
