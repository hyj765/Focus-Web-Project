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

  @Override
  public void deleteByInterviewRoomIdAndEvaluatorId(Long interviewRoomId, Long evaluatorId) {
    jpaQueryFactory
            .delete(qApplicantEvaluator)
            .where(
                    eqInterviewRoomId(interviewRoomId),
                    eqEvaluatorId(evaluatorId)
            )
            .execute();

  }

  @Override
  public void deleteByInterviewRoomIdAndApplicantId(Long interviewRoomId, Long applicantId) {
    jpaQueryFactory
            .delete(qApplicantEvaluator)
            .where(
                    eqInterviewRoomId(interviewRoomId),
                    eqApplicantId(applicantId)
            )
            .execute();

  }

  private BooleanExpression eqInterviewRoomId(Long interviewRoomId) {
    if(interviewRoomId.equals(null)){
      return null;
    }
    return qInterviewRoom.id.eq(interviewRoomId);
  }

  private BooleanExpression eqEvaluatorId(Long evaluatorId) {
    if(evaluatorId.equals(null)){
      return null;
    }
    return qInterviewRoom.id.eq(evaluatorId);
  }

  private BooleanExpression eqApplicantId(Long applicantId) {
    if(applicantId.equals(null)){
      return null;
    }
    return qInterviewRoom.id.eq(applicantId);
  }
}
