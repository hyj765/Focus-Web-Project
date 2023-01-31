package com.bb.focus.api.service;

import com.bb.focus.db.entity.interview.Interview;

public interface InterviewService {

  Interview findInterviewById(Long interviewId);
}
