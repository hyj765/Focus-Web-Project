package com.bb.focus.api.service;

import com.bb.focus.api.request.InterviewRoomReq;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.entity.helper.ApplicantInterviewRoom;
import com.bb.focus.db.entity.helper.EvaluatorInterviewRoom;
import com.bb.focus.db.entity.interview.Interview;
import com.bb.focus.db.entity.interview.InterviewRoom;
import com.bb.focus.db.entity.interview.Room;
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

    //지원자-평가자 테이블에 데이터 넣기... 서비스 불러와서 함수 호출하고 외래키 파라미터 세팅해주기.
//    for(int e = 0, elen = evaluatorIds.length; e < elen; e++){
//      for(int a = 0, alen = applicantIds.length; a < alen; a++){
//
//      }
//    }


  }

  @Override
  public Optional<InterviewRoom> findById(Long id) {
        return interviewRoomRepository.findById(id);
  }

}
