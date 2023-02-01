package com.bb.focus.api.service;

import com.bb.focus.api.request.InterviewRoomReq;
import com.bb.focus.db.entity.interview.InterviewRoom;

import java.util.Optional;

public interface InterviewRoomService {

  //면접 일정 생성
    //면접 일정에 평가자 등록
    //면접 일정에 지원자 등록
  public void createInterviewRoom(InterviewRoomReq interviewRoomReq);

  //면접 일정 수정

  //면접 일정 삭제


  Optional<InterviewRoom> findById(Long id);
}
