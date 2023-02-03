package com.bb.focus.db.repository;

import com.bb.focus.api.response.ApplicantRes;
import com.bb.focus.api.response.EvaluatorRes;
import com.bb.focus.db.entity.interview.InterviewRoom;
import java.util.List;
import java.util.Optional;

public interface InterviewRoomCustomRepository {

  List<EvaluatorRes> findEvaluatorsByInterviewId(Long interviewRoomId);

  List<ApplicantRes> findApplicantsByInterviewId(Long interviewRoomId);
}
