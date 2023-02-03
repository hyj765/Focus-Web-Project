package com.bb.focus.db.repository;

import com.bb.focus.api.response.ApplicantRes;
import com.bb.focus.api.response.EvaluatorRes;
import com.bb.focus.db.entity.applicant.QApplicant;
import com.bb.focus.db.entity.evaluator.QEvaluator;
import com.bb.focus.db.entity.helper.QApplicantInterviewRoom;
import com.bb.focus.db.entity.helper.QEvaluatorInterviewRoom;
import com.bb.focus.db.entity.interview.QInterviewRoom;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InterviewRoomCustomRepositoryImpl implements InterviewRoomCustomRepository {

  private final JPAQueryFactory jpaQueryFactory;

  QInterviewRoom qInterviewRoom = QInterviewRoom.interviewRoom;
  QEvaluator qEvaluator = QEvaluator.evaluator;
  QApplicant qApplicant = QApplicant.applicant;
  QEvaluatorInterviewRoom qEvaluatorInterviewRoom = QEvaluatorInterviewRoom.evaluatorInterviewRoom;
  QApplicantInterviewRoom qApplicantInterviewRoom = QApplicantInterviewRoom.applicantInterviewRoom;

  public List<EvaluatorRes> findEvaluatorsByInterviewId(Long interviewRoomId) {

    return jpaQueryFactory
        .select(Projections.constructor(EvaluatorRes.class,
            qEvaluator.id,
            qEvaluator.name,
            qEvaluator.code,
            qEvaluator.department,
            qEvaluator.position,
            qEvaluator.image,
            qEvaluator.userId,
            qEvaluator.tel,
            qEvaluator.email
        ))
        .from(qEvaluator)
        .innerJoin(qEvaluatorInterviewRoom).on(qEvaluatorInterviewRoom.evaluator.eq(qEvaluator))
        .innerJoin(qInterviewRoom).on(qEvaluatorInterviewRoom.interviewRoom.eq(qInterviewRoom))
        .where(eqInterviewRoomId(interviewRoomId))
        .orderBy(qEvaluator.code.asc())
        .fetch();
  }

  public List<ApplicantRes> findApplicantsByInterviewId(Long interviewRoomId) {

    return jpaQueryFactory
        .select(Projections.constructor(ApplicantRes.class,
            qApplicant.id,
            qApplicant.name,
            qApplicant.userId,
            qApplicant.code,
            qApplicant.image,
            qApplicant.tel,
            qApplicant.email
        ))
        .from(qApplicant)
        .innerJoin(qApplicantInterviewRoom).on(qApplicantInterviewRoom.applicant.eq(qApplicant))
        .innerJoin(qInterviewRoom).on(qApplicantInterviewRoom.interviewRoom.eq(qInterviewRoom))
        .where(eqInterviewRoomId(interviewRoomId))
        .orderBy(qApplicant.code.asc())
        .fetch();
  }

  private BooleanExpression eqInterviewRoomId(Long interviewRoomId) {
    if(interviewRoomId.equals(null)){
      return null;
    }
    return qInterviewRoom.id.eq(interviewRoomId);
  }
}
