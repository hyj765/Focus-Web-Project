package com.bb.focus.db.repository;

import com.bb.focus.db.entity.admin.ServiceAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaticalRepository extends JpaRepository<ServiceAdmin, Long> {

}
