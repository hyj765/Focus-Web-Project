package com.bb.focus.api.service;

import com.bb.focus.api.request.RoomReq;
import com.bb.focus.common.util.EncryptionUtils;
import com.bb.focus.db.entity.interview.Room;
import com.bb.focus.db.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

  @Autowired
  InterviewService interviewService;

  @Autowired
  RoomRepository roomRepository;

  @Override
  public Room createRoom(RoomReq.Create roomInfo) {
    Room room = new Room();
    room.setInterview(interviewService.findInterviewById(roomInfo.getInterviewId()));
    return roomRepository.save(room);
  }

  @Override
  public Long updateRoomById(Room room) {
//    Room room = new Room();
//    room.setId(roomId);
//    room.setRealCode(EncryptionUtils.encryptSHA256("real" + roomId));
//    room.setWaitCode(EncryptionUtils.encryptSHA256("wait" + roomId));
    String newRealCode = EncryptionUtils.encryptSHA256("real" + room.getId());
    String newWaitCode = EncryptionUtils.encryptSHA256("wait" + room.getId());
    roomRepository.save(Room.builder()
        .id(room.getId())
        .interview(room.getInterview())
        .realCode(newRealCode)
        .waitCode(newWaitCode).build());
    return room.getId();
  }

  @Override
  public Room autoCreateRoom(Long interviewId) {
    Room room = new Room();
    room.setInterview(interviewService.findInterviewById(interviewId));
    return roomRepository.save(room);
  }
}
