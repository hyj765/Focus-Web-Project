package com.bb.focus.api.service;

import com.bb.focus.db.entity.interview.Interview;
import com.bb.focus.api.request.InterviewReq;

public interface InterviewService {

  Interview findInterviewById(Long interviewId);

  public void createInterview(Long processId, InterviewReq interviewReq);

}
