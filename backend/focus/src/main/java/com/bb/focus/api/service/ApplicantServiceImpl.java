package com.bb.focus.api.service;

import com.bb.focus.api.request.ApplicantInfoReq;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.applicant.school.ApplicantCollege;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.repository.ApplicantRepository;
import com.bb.focus.db.repository.CompanyAdminRepository;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService{

  private final ApplicantRepository applicantRepository;
  private final CompanyAdminRepository companyAdminRepository;

  /**
   * 지원자 계정 생성
   */
  public Long create(Long comapnyAdminId, ApplicantInfoReq applicantInfoReq) {
    Applicant applicant = new Applicant();

    applicant.setName(applicantInfoReq.getName());
    applicant.setCode(applicantInfoReq.getCode());
    applicant.setGender(applicantInfoReq.getGender());
    applicant.setBirth(applicantInfoReq.getBirth());
    applicant.setImage(applicantInfoReq.getImage());
    applicant.setEmail(applicantInfoReq.getEmail());
    applicant.setTel(applicantInfoReq.getTel());
    applicant.setResume(applicantInfoReq.getResume());
    applicant.setDegree(applicantInfoReq.getDegree());
    applicant.setAwardCount(applicantInfoReq.getAwardCount());
    applicant.setActivityCount(applicantInfoReq.getActivityCount());

    //대학교, 대학원 매핑
    //아이디로 2년제 대학교, 4년제 대학교, 대학원의 정보를 가져온다.
    //가져온 정보를 setter로 applicant에 매핑한다.
    /*
    if(applicantInfoReq.getCollegeId() != null){
      //조회
      ApplicantCollege applicantCollege = ApplicantCollegeRepository.findById(applicantInfoReq.getCollegeId());
      applicant.setApplicationCollege(applicantCollege);
    }
    ....
     */

    CompanyAdmin companyAdmin = companyAdminRepository.findById(comapnyAdminId).orElseThrow(IllegalArgumentException::new);
    companyAdmin.addApplicant(applicant);

    applicantRepository.save(applicant);
    return applicant.getId();
  }

  /**
   * 지원자 아이디, 비밀번호 자동생성 및 할당
   * 생성 규칙] 아이디 : 기업이름 + A + 지원자 수험번호
   *          비밀번호 : 랜덤 생성 문자열
   */
  public void autoAssignAccount(Long id) {

    Applicant applicant = applicantRepository.findById(id).orElseThrow(IllegalArgumentException::new);

    String newId = applicant.getCompanyAdmin().getCompanyName() + "A" + applicant.getCode();
    String newPwd = getRandomString();

    applicant.setUserId(newId);
    applicant.setPwd(newPwd);
  }

  /**
   * 지원자의 기본정보를 수정한다.
   * 수정 항목] 이름, 수험번호, 성별, 생년월일, 사진, 이메일, 전화번호, 자기소개서, 학위, 수상횟수, 대외활동 횟수, 대학정보(2, 4, 대학원)
   */
  public Long updateApplicantInfo(Long id, ApplicantInfoReq applicantInfoReq) {
    Applicant applicant = applicantRepository.findById(id).orElseThrow(IllegalArgumentException::new);

    applicant.setName(applicantInfoReq.getName());
    applicant.setCode(applicantInfoReq.getCode());
    applicant.setGender(applicantInfoReq.getGender());
    applicant.setBirth(applicantInfoReq.getBirth());
    applicant.setImage(applicantInfoReq.getImage());
    applicant.setEmail(applicantInfoReq.getEmail());
    applicant.setTel(applicantInfoReq.getTel());
    applicant.setResume(applicantInfoReq.getResume());
    applicant.setDegree(applicantInfoReq.getDegree());
    applicant.setAwardCount(applicantInfoReq.getAwardCount());
    applicant.setActivityCount(applicantInfoReq.getActivityCount());

    //대학 정보 수정..

    return applicant.getId();
  }

  /**
   * 지원자 삭제
   */
  public void removeApplicant(Long id) {
    applicantRepository.deleteById(id);
  }

  /**
   * 현재 회사에 소속되어있는 모든 지원자 목록을 조회한다.
   */
  public List<Applicant> findAllApplicants(Long companyAdminId) {
    List<Applicant> applicants = applicantRepository.findAllApplicantsByCompanyAdminId(companyAdminId);
    return applicants;
  }

  /**
   * 특정 지원자를 조회한다.
   */
  public Applicant findApplicant(Long id) {
    return applicantRepository.findById(id).orElseThrow(IllegalArgumentException::new);
  }

  /**
   * 비밀번호 자동생성에 사용되는 랜덤 문자열 생성기
   */
  private String getRandomString() {
    int leftLimit = 48;     //숫자 0
    int rightLimit = 122;   //영문자 z
    int targetStringLength = 10;    //10글자로 생성
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

    return generatedString;
  }

}
