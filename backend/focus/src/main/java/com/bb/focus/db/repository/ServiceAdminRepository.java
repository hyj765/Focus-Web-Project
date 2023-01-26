package com.bb.focus.db.repository;

import com.bb.focus.db.entity.admin.ServiceAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceAdminRepository extends JpaRepository<ServiceAdmin, Long>, ServiceAdminCustomRepository {
    Optional<ServiceAdmin> findByUserId(String userId);
}
