package com.bb.focus.db.repository;

import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.evaluator.Evaluator;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyAdminCustomRepository {

  Optional<CompanyAdmin> findCompanyAdminByUserId(String userId);

  List<CompanyAdmin> findAll();

  Long updateCompanyAdminByUserId(CompanyAdmin companyAdmin);

}
