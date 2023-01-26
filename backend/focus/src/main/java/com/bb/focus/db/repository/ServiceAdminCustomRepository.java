package com.bb.focus.db.repository;

import com.bb.focus.db.entity.admin.ServiceAdmin;
import com.bb.focus.db.entity.company.CompanyAdmin;
import java.util.List;
import java.util.Optional;

public interface ServiceAdminCustomRepository {

  Optional<ServiceAdmin> findServiceAdminByUserId(String userId);

}
