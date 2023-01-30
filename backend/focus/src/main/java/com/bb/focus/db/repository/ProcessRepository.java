package com.bb.focus.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<Process, Long> , ProcessCustomRepository{

}
