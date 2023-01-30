package com.bb.focus.db.repository;

import com.bb.focus.db.entity.process.Process;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<Process, Long> , ProcessCustomRepository{

}
