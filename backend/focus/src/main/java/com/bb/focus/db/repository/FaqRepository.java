package com.bb.focus.db.repository;

import com.bb.focus.db.entity.admin.Faq;
import com.bb.focus.db.entity.interview.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long>,
    FaqCustomRepository {

  List<Faq> findAll();

  List<Faq> findByAuthRange(Byte userRole);

}
