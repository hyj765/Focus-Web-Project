package com.bb.focus.api.service;

import com.bb.focus.api.request.EvaluatorInfoReq;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.repository.CompanyAdminRepository;
import com.bb.focus.db.repository.EvaluatorRepository;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EvaluatorServiceImpl implements EvaluatorService{

  private final CompanyAdminRepository companyAdminRepository;
  private final EvaluatorRepository evaluatorRepository;

  private final MailService mailService;
  private final PasswordEncoder passwordEncoder;

  /**
   * 평가자 계정 생성
   */
  @Transactional
  public Long create(Long companyAdminId, EvaluatorInfoReq evaluatorInfo) {

    Evaluator evaluator = new Evaluator();

    evaluator.setName(evaluatorInfo.getName());
    evaluator.setCode(evaluatorInfo.getCode());
    evaluator.setDepartment(evaluatorInfo.getDepartment());
    evaluator.setPosition(evaluatorInfo.getPosition());
    evaluator.setTel(evaluatorInfo.getTel());
    evaluator.setEmail(evaluatorInfo.getEmail());
    evaluator.setImage(evaluatorInfo.getImage());

    validateDuplicateEvaluator(evaluator);

    CompanyAdmin companyAdmin = companyAdminRepository.findById(companyAdminId).orElseThrow(IllegalArgumentException::new);
    companyAdmin.addEvaluator(evaluator);

    evaluatorRepository.save(evaluator);
    return evaluator.getId();
  }

  /**
   * 평가자 id, pwd 자동 생성 및 할당
   * 생성 규칙 ] 아이디 : 기업이름 + E + 평가자사번
   *           비밀번호 : 랜덤 생성 문자열
   */
  @Transactional
  public void autoAssignAccount(Long id) {

    Evaluator evaluator = evaluatorRepository.findById(id).orElseThrow(IllegalArgumentException::new);

    String newId = evaluator.getCompanyAdmin().getCompanyName() + "E" + evaluator.getCode();
    String newPwd = getRandomString();

    //메일
//    Map<String, String> content = new HashMap<>();
//    content.put("id", newId);
//    content.put("pwd", newPwd);
//    mailService.sendAccountMail(evaluator.getEmail(), content);

    //암호화
    String encodedPwd = passwordEncoder.encode(newPwd);

    evaluator.setUserId(newId);
    evaluator.setPwd(encodedPwd);
  }

  /**
   * 평가자의 기본 정보를 수정한다.
   * 수정 항목 ] 이름, 사번, 부서, 직급, 전화번호, 이메일, 사진
   */
  @Transactional
  public void updateEvaluatorInfo(Long id, EvaluatorInfoReq evaluatorInfo) {
    Evaluator evaluator = evaluatorRepository.findById(id).orElseThrow(IllegalAccessError::new);

    evaluator.setName(evaluatorInfo.getName());
    evaluator.setCode(evaluatorInfo.getCode());
    evaluator.setDepartment(evaluatorInfo.getDepartment());
    evaluator.setPosition(evaluatorInfo.getPosition());
    evaluator.setTel(evaluatorInfo.getTel());
    evaluator.setEmail(evaluatorInfo.getEmail());
    evaluator.setImage(evaluatorInfo.getImage());
  }

  @Transactional
  public void removeEvaluator(Long id) {
    evaluatorRepository.deleteById(id);
  }

  @Override
  public Page<Evaluator> findAllEvaluatorsUsePaging(Pageable pageable, Long companyAdminId) {
    Page<Evaluator> evaluators = evaluatorRepository.findAllEvaluatorsByCompanyAdminIdUsePaging(pageable, companyAdminId);
    return evaluators;
  }

  @Override
  public List<Evaluator> findAllEvaluators(Long companyAdminId) {
    List<Evaluator> evaluators = evaluatorRepository.findAllEvaluatorsByCompanyAdminId(companyAdminId);
    return evaluators;
  }

  @Override
  public Evaluator findEvaluator(Long id) {
    Evaluator evaluator = evaluatorRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    return evaluator;
  }

  /**
   * 평가자 이메일로 중복 회원 검증
   */
  private void validateDuplicateEvaluator(Evaluator evaluator) {

    Optional<Evaluator> findEvaluator = evaluatorRepository.findAllByEmail(evaluator.getEmail());
    if(findEvaluator.isPresent()){
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
  }

  /**
   * 비밀번호 자동 생성에 사용되는 랜덤 문자열 생성
   */
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
