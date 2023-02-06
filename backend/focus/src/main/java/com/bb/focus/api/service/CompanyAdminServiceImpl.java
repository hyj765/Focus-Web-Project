package com.bb.focus.api.service;

import com.bb.focus.api.request.CompanyAdminRegisterPostReq;
import com.bb.focus.api.response.ApplicantRes;
import com.bb.focus.api.response.InterviewRoomRes;
import com.bb.focus.api.response.ProcessRes;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.interview.InterviewRoom;
import com.bb.focus.db.entity.process.Process;
import com.bb.focus.db.repository.ApplicantRepository;
import com.bb.focus.db.repository.CompanyAdminRepository;
import com.bb.focus.db.repository.InterviewRepository;
import com.bb.focus.db.repository.InterviewRoomRepository;
import com.bb.focus.db.repository.ProcessRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("companyAdminService")
public class CompanyAdminServiceImpl implements CompanyAdminService {

  CompanyAdminRepository companyAdminRepository;
  ProcessRepository processRepository;
  InterviewRoomRepository interviewRoomRepository;

  @Autowired
  public CompanyAdminServiceImpl(CompanyAdminRepository companyAdminRepo
                                ,ProcessRepository processRepo
                                , InterviewRoomRepository interviewRoomRepo){
    companyAdminRepository = companyAdminRepo;
    processRepository = processRepo;
    interviewRoomRepository = interviewRoomRepo;
  }
//  @Autowired
//  PasswordEncoder passwordEncoder;

  @Override
  public CompanyAdmin createCompanyAdmin(CompanyAdminRegisterPostReq userRegisterInfo) {
    CompanyAdmin companyAdmin = new CompanyAdmin();
    companyAdmin.setUserId(userRegisterInfo.getUserId());
//    companyAdmin.setPwd(passwordEncoder.encode(userRegisterInfo.getPwd()));
    companyAdmin.setPwd(userRegisterInfo.getPwd());
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
    companyAdmin.setUserId(userUpdateInfo.getUserId());
//    companyAdmin.setPwd(passwordEncoder.encode(userUpdateInfo.getPwd()));
    companyAdmin.setPwd(userUpdateInfo.getPwd());
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

  public int getAllEvaluationCount(Long companyAdminId){
    CompanyAdmin companyAdmin=companyAdminRepository.findCompanyAdminById(companyAdminId);
    return companyAdmin.getEvaluatorList().size();
  }

  public int getAllApplicantCount(Long companyAdminId){
    CompanyAdmin companyAdmin=companyAdminRepository.findCompanyAdminById(companyAdminId);
    int applicantCount =0;

    List<Process> processes = companyAdmin.getProcessList();
    for(Process process:processes){
        applicantCount+=process.getApplicantList().size();
    }

    return applicantCount;
  }
  public List<ProcessRes> getAllProcess(Long companyAdminId){
    List<ProcessRes> processResList = processRepository.findAllExpectedProcess(companyAdminId);
    return processResList;
  }
  public List<InterviewRoomRes> getAllReservedInterview(Long processId){
    Process process = processRepository.findById(processId).orElseThrow(IllegalAccessError::new);
    Long cur_step = (long)process.getCurrentStep();
    List<InterviewRoom> interviewRoomList=interviewRoomRepository.findByProcessIdAndCurrentStep(processId,cur_step);
    List<InterviewRoomRes> interviewRoomResList= new ArrayList<>();

    for(InterviewRoom interviewRoom:interviewRoomList){
      if(interviewRoom.getStartTime().isAfter(LocalDateTime.now())) {
          InterviewRoomRes interviewRoomRes = new InterviewRoomRes(interviewRoom);
          interviewRoomResList.add(interviewRoomRes);
      }
    }

    return interviewRoomResList;
  }

  public List<ApplicantRes> getAllInterviewPerPassApplicant(){


    return null;
  }


}
