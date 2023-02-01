package com.bb.focus.db.repository;

import com.bb.focus.db.entity.helper.QApplicantEvaluator;
import com.bb.focus.db.entity.interview.QInterviewRoom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApplicantEvaluatorCustomRepositoryImpl implements ApplicantEvaluatorCustomRepository{

  private final JPAQueryFactory jpaQueryFactory;
  QInterviewRoom qInterviewRoom = QInterviewRoom.interviewRoom;
  QApplicantEvaluator qApplicantEvaluator = QApplicantEvaluator.applicantEvaluator;

  public void deleteByInterviewRoomId(Long interviewRoomId) {

    jpaQueryFactory
        .delete(qInterviewRoom)
        .where(eqInterviewRoomId(interviewRoomId))
        .execute();

  }

  private BooleanExpression eqInterviewRoomId(Long interviewRoomId) {
    if(interviewRoomId.equals(null)){
      return null;
    }
    return qInterviewRoom.id.eq(interviewRoomId);
  }
}
