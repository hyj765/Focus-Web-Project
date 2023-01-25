package com.bb.focus.db.repository;

import com.bb.focus.db.entity.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long>, ApplicantCustomRepository{

}
