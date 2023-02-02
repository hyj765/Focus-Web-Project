package com.bb.focus.db.repository;

import java.util.List;

public interface ApplicantEvaluatorCustomRepository {

  void deleteByInterviewRoomIdAndEvaluatorId(Long interviewRoomId, Long evaluatorId);

  void deleteByInterviewRoomIdAndApplicantId(Long interviewRoomId, Long applicantId);

  List<Long> findApplicantIds(Long interviewRoomId);

  List<Long> findEvaluatorIds(Long interviewRoomId);

}
