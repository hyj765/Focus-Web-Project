package com.bb.focus.api.service;

import com.bb.focus.db.entity.interview.InterviewRoom;
import com.bb.focus.db.repository.InterviewRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("roomService")
public class InterviewRoomServiceImpl implements InterviewRoomService {

    @Autowired
    InterviewRoomRepository interviewRoomRepository;

    @Override
    public Optional<InterviewRoom> findById(Long id) {
        return interviewRoomRepository.findById(id);
    }
}
