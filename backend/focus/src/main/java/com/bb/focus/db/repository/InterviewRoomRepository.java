package com.bb.focus.db.repository;

import com.bb.focus.db.entity.interview.InterviewRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRoomRepository extends JpaRepository<InterviewRoom, Long>,
        InterviewRoomCustomRepository {


}
