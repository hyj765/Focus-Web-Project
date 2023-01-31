package com.bb.focus.api.service;

import com.bb.focus.db.entity.applicant.Status;

public interface EvaluationService {

  boolean LoggingUserPass(Long processId,Long applicantId,  Status status);

}
