package com.bb.focus.db.repository;

public interface ApplicantEvaluatorCustomRepository {

  void deleteByInterviewRoomIdAndEvaluatorId(Long interviewRoomId, Long evaluatorId);

  void deleteByInterviewRoomIdAndApplicantId(Long interviewRoomId, Long applicantId);

}
