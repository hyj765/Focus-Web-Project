package com.bb.focus.db.repository;

import com.bb.focus.db.entity.admin.ServiceNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceNoticeRepository extends JpaRepository<ServiceNotice, Long>,
    ServiceNoticeCustomRepository {


}
