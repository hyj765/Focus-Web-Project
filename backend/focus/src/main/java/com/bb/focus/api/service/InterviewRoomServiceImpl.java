package com.bb.focus.api.service;

import com.bb.focus.api.request.InterviewRoomReq;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.entity.helper.ApplicantInterviewRoom;
import com.bb.focus.db.entity.helper.EvaluatorInterviewRoom;
import com.bb.focus.db.entity.interview.Interview;
import com.bb.focus.db.entity.interview.InterviewRoom;
import com.bb.focus.db.entity.interview.Room;
import com.bb.focus.db.repository.ApplicantEvaluatorRepository;
import com.bb.focus.db.repository.ApplicantInterviewRoomRepository;
import com.bb.focus.db.repository.ApplicantRepository;
import com.bb.focus.db.repository.EvaluatorInterviewRoomRepository;
import com.bb.focus.db.repository.EvaluatorRepository;
import com.bb.focus.db.repository.InterviewRepository;
import com.bb.focus.db.repository.InterviewRoomRepository;
import com.bb.focus.db.repository.RoomRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewRoomServiceImpl implements InterviewRoomService {

  private final InterviewRoomRepository interviewRoomRepository;
  private final EvaluatorInterviewRoomRepository evaluatorInterviewRoomRepository;
  private final ApplicantInterviewRoomRepository applicantInterviewRoomRepository;
  private final InterviewRepository interviewRepository;
  private final RoomRepository roomRepository;
  private final EvaluatorRepository evaluatorRepository;
  private final ApplicantRepository applicantRepository;
  private final EvaluationService evaluationService;
  private final ApplicantEvaluatorRepository applicantEvaluatorRepository;

  /**
   * 면접실 기본 정보 + 평가자 리스트 + 지원자 리스트를 받아와서 면접실을 생성한다.
   * @param interviewRoomReq
   */
  @Transactional
  public void createInterviewRoom(InterviewRoomReq interviewRoomReq) {

    InterviewRoom interviewRoom = new InterviewRoom();

    //기본정보 setting
    interviewRoom.setName(interviewRoomReq.getName());
    interviewRoom.setStartTime(interviewRoomReq.getStartTime());
    interviewRoom.setEndTime(interviewRoomReq.getEndTime());

    Duration duration = Duration.between(interviewRoom.getStartTime(), interviewRoom.getEndTime());
    int interviewDuration = (int)(duration.getSeconds() / 60);

    interviewRoom.setDuration(interviewDuration);

    //외래키 setting
    Long interviewId = interviewRoomReq.getInterviewId();
    Interview interview = interviewRepository.findById(interviewId).orElseThrow(IllegalArgumentException::new);
    interviewRoom.setInterview(interview);

    Long roomId = interviewRoomReq.getRoomId();
    Room room = roomRepository.findById(roomId).orElseThrow(IllegalArgumentException::new);
    interviewRoom.setRoom(room);

    interviewRoomRepository.save(interviewRoom);

    //평가자 리스트 setting
    Long[] evaluatorIds = interviewRoomReq.getEvaluators();
    for(Long evaluatorId : evaluatorIds){
      EvaluatorInterviewRoom evaluatorInterviewRoom = new EvaluatorInterviewRoom();
      Evaluator evaluator = evaluatorRepository.findById(evaluatorId).orElseThrow(IllegalArgumentException::new);
      evaluatorInterviewRoom.setEvaluator(evaluator);
      evaluatorInterviewRoom.setInterviewRoom(interviewRoom);
      evaluatorInterviewRoomRepository.save(evaluatorInterviewRoom);
    }

    //지원자 리스트 setting
    Long[] applicantIds = interviewRoomReq.getApplicants();
    for(Long applicantId : applicantIds){
      ApplicantInterviewRoom applicantInterviewRoom = new ApplicantInterviewRoom();
      Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(IllegalArgumentException::new);
      applicantInterviewRoom.setApplicant(applicant);
      applicantInterviewRoom.setInterviewRoom(interviewRoom);
      applicantInterviewRoomRepository.save(applicantInterviewRoom);
    }

    //지원자-평가자 테이블에 데이터 넣기
    for(int e = 0, elen = evaluatorIds.length; e < elen; e++){
      for(int a = 0, alen = applicantIds.length; a < alen; a++){
        evaluationService.createApplicantEvaluator(interviewId, evaluatorIds[e], applicantIds[a]);
      }
    }
  }

  @Transactional
  public Long updateInterviewRoom(Long interviewRoomId, InterviewRoomReq interviewRoomReq) {

    InterviewRoom interviewRoom = interviewRoomRepository.findById(interviewRoomId).orElseThrow(IllegalArgumentException::new);

    interviewRoom.setName(interviewRoomReq.getName());
    interviewRoom.setStartTime(interviewRoomReq.getStartTime());
    interviewRoom.setEndTime(interviewRoomReq.getEndTime());

    Duration duration = Duration.between(interviewRoom.getStartTime(), interviewRoom.getEndTime());
    int interviewDuration = (int)(duration.getSeconds() / 60);

    interviewRoom.setDuration(interviewDuration);

    Long roomId = interviewRoomReq.getRoomId();
    Room room = roomRepository.findById(roomId).orElseThrow(IllegalArgumentException::new);
    interviewRoom.setRoom(room);


    return interviewRoom.getId();
  }

  @Transactional
  public void removeInterviewRoom(Long interviewRoomId) {
    interviewRoomRepository.deleteById(interviewRoomId);
//    applicantEvaluatorRepository.deleteByInterviewRoomId(interviewRoomId);    //jpa 엔티티 수정하고 casecade로 설정하면 필요 없다.
  }

  @Transactional
  public void addEvaluatorToInterviewRoom(Long interviewRoomId, Long evaluatorId) {

    InterviewRoom interviewRoom = interviewRoomRepository.findById(interviewRoomId).orElseThrow(IllegalArgumentException::new);

    Evaluator evaluator = evaluatorRepository.findById(evaluatorId).orElseThrow(IllegalArgumentException::new);

    EvaluatorInterviewRoom evaluatorInterviewRoom = new EvaluatorInterviewRoom();
    evaluatorInterviewRoom.setInterviewRoom(interviewRoom);
    evaluatorInterviewRoom.setEvaluator(evaluator);

    evaluatorInterviewRoomRepository.save(evaluatorInterviewRoom);
  }

  @Transactional
  public void addApplicantToInterviewRoom(Long interviewRoomId, Long applicantId) {

    InterviewRoom interviewRoom = interviewRoomRepository.findById(interviewRoomId).orElseThrow(IllegalArgumentException::new);

    Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(IllegalArgumentException::new);

    ApplicantInterviewRoom applicantInterviewRoom = new ApplicantInterviewRoom();
    applicantInterviewRoom.setInterviewRoom(interviewRoom);
    applicantInterviewRoom.setApplicant(applicant);

    applicantInterviewRoomRepository.save(applicantInterviewRoom);
  }

  @Transactional
  public void removeEvaluator(Long interviewRoomId, Long evaluatorId) {
    evaluatorInterviewRoomRepository.deleteByInterviewRoomIdAndEvaluatorId(interviewRoomId, evaluatorId);
  }

  @Transactional
  public void removeApplicant(Long interviewRoomId, Long applicantId) {
    applicantInterviewRoomRepository.deleteByInterviewRoomIdAndApplicantId(interviewRoomId, applicantId);
  }

  @Override
  public Optional<InterviewRoom> findById(Long id) {
        return interviewRoomRepository.findById(id);
  }

}
