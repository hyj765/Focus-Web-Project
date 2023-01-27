package com.bb.focus.db.repository;

import com.bb.focus.api.response.EvaluatorRes;
import com.bb.focus.common.util.QueryDslUtil;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.company.QCompanyAdmin;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.entity.evaluator.QEvaluator;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EvaluatorCustomRepositoryImpl implements EvaluatorCustomRepository{

  private final JPAQueryFactory jpaQueryFactory;

  QEvaluator qEvaluator = QEvaluator.evaluator;
  QCompanyAdmin qCompanyAdmin = QCompanyAdmin.companyAdmin;

  /**
   * 페이징 사용 + 기업관리자 아이디로 모든 평가자 조회
   */
  public Page<Evaluator> findAllEvaluatorsByCompanyAdminIdUsePaging(Pageable pageable, Long companyAdminId) {

    List<OrderSpecifier> ORDERS = getAllOrderSpecifiers(pageable);

    List<Evaluator> results = jpaQueryFactory
        .selectFrom(qEvaluator)
        .join(qEvaluator.companyAdmin, qCompanyAdmin)
        .where(eqCompanyAdminId(companyAdminId))
        .orderBy(ORDERS.stream().toArray(OrderSpecifier[]::new))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

//    List<Evaluator> content = results.getResults();
//    long total = results.getTotal();

//    return new PageImpl<>(content, pageable, total);
    return new PageImpl<>(results, pageable, results.size());
  }


  /**
   * 기업 관리자 시퀀스넘버로 모든 평가자 조회
   */
  public List<Evaluator> findAllEvaluatorsByCompanyAdminId(Long companyAdminId) {
    return jpaQueryFactory
        .selectFrom(qEvaluator)
        .join(qEvaluator.companyAdmin, qCompanyAdmin)
        .where(eqCompanyAdminId(companyAdminId))
        .fetch();
  }

  @Override
  public Evaluator findEvaluatorByUserId(String userId) {
    Evaluator evaluator = jpaQueryFactory.select(qEvaluator).from(qEvaluator)
        .where(qEvaluator.userId.eq(userId)).fetchOne();
    return evaluator;
  }

  private BooleanExpression eqCompanyAdminId(Long companyAdminId){

    if(companyAdminId.equals(null)) return null;
    return qCompanyAdmin.id.eq(companyAdminId);
  }

  private List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable){
    List<OrderSpecifier> ORDERS = new ArrayList<>();

    if(!isEmpty(pageable.getSort())){
      for(Sort.Order order : pageable.getSort()){
        Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

        switch(order.getProperty()){
          case "department":
            OrderSpecifier<?> department = QueryDslUtil.getSortedColumn(direction, QEvaluator.evaluator, "department");
            ORDERS.add(department);
            break;
          case "position":
            OrderSpecifier<?> position = QueryDslUtil.getSortedColumn(direction, QEvaluator.evaluator, "position");
            ORDERS.add(position);
            break;
          case "name":
            OrderSpecifier<?> name = QueryDslUtil.getSortedColumn(direction, QEvaluator.evaluator, "name");
            ORDERS.add(name);
            break;
          default:
            break;
        }
      }
    }
    return ORDERS;
  }
}
