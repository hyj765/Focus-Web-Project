package com.bb.focus.db.entity.interview;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interview_rooms")
@Getter
@Setter
@NoArgsConstructor
public class InterviewRoom {

    @Id
    @GeneratedValue
    @Column(name = "interview_room_id")
    private Long id;

    private Long roomId;

    private Long companyAdminId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_chat_id")
    private RoomChat roomChat;


    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;
    private int curEvaluatorCount;
    private int curApplicantCount;
}
