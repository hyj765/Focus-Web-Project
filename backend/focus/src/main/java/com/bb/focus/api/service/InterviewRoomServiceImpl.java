package com.bb.focus.api.service;

import com.bb.focus.api.request.InterviewRoomReq;
import com.bb.focus.db.entity.interview.InterviewRoom;
import com.bb.focus.db.repository.ApplicantInterviewRoomRepository;
import com.bb.focus.db.repository.EvaluatorInterviewRoomRepository;
import com.bb.focus.db.repository.InterviewRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewRoomServiceImpl implements InterviewRoomService {

  private final InterviewRoomRepository interviewRoomRepository;
  private final EvaluatorInterviewRoomRepository evaluatorInterviewRoomRepository;
  private final ApplicantInterviewRoomRepository applicantInterviewRoomRepository;

  /**
   * 면접실 기본 정보 + 평가자 리스트 + 지원자 리스트를 받아와서 면접실을 생성한다.
   * @param interviewRoomReq
   */
  @Transactional
  public void createInterviewRoom(InterviewRoomReq interviewRoomReq) {

    InterviewRoom interviewRoom = new InterviewRoom();

    //기본정보 setting


  }
}
