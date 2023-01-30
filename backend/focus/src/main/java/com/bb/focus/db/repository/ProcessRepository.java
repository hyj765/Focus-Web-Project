package com.bb.focus.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bb.focus.db.entity.process.Process;

public interface ProcessRepository extends JpaRepository<Process, Long> , ProcessCustomRepository{

}
