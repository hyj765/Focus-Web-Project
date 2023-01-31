package com.bb.focus.api.service;

import com.bb.focus.api.request.InterviewReq;

public interface InterviewService {

  public void createInterview(Long processId, InterviewReq interviewReq);

}
