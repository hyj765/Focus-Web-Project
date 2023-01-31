package com.bb.focus.api.service;

import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.entity.interview.Interview;
import com.bb.focus.db.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("interviewService")
public class InterviewServiceImpl implements InterviewService{

  @Autowired
  InterviewRepository interviewRepository;

  @Override
  public Interview findInterviewById(Long interviewId) {
    Interview interview = interviewRepository.findInterviewById(interviewId);
    return interview;
  }
}
