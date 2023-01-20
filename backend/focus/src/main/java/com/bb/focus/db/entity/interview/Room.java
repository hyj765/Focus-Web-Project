package com.bb.focus.db.entity.interview;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="room")
//@NoArgsConstructor
@Table(name="room")
public class Room {

  @Id
  @GeneratedValue
  private int roomId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "interview_id")
  private Interview interview;

  private String startTime;
  private String endTime;
  private String link;

  @OneToMany(mappedBy = "room")
  private List<InterviewRoom> interviewRoomList = new ArrayList<>();


}
