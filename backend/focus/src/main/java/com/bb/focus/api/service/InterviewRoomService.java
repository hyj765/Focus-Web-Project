package com.bb.focus.api.service;

import com.bb.focus.db.entity.interview.InterviewRoom;

import java.util.Optional;

public interface InterviewRoomService {

    Optional<InterviewRoom> findById(Long id);

}
