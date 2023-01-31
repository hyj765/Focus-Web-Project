package com.bb.focus.db.repository;

import com.bb.focus.db.entity.interview.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository  extends JpaRepository<Interview, Long>, InterviewCustomRepository{

}
