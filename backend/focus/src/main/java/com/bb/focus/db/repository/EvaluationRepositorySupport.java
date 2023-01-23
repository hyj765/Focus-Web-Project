package com.bb.focus.db.repository;

import com.bb.focus.db.entity.evaluator.Evaluator;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EvaluationRepositorySupport {

  private final EntityManager em;

  public List<Evaluator> findAllWithCompanyAdmin(){
    return em.createQuery(
        "select e from Evaluator e "+
            " join fetch e.companyAdmin ca", Evaluator.class).getResultList();
  }

}
