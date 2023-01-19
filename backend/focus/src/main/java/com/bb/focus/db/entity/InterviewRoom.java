package com.bb.focus.db.entity;

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
    private int id;

    private int roomId;
    private int companyAdminId;
    private int roomChattingId;

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;
    private int curEvaluatorCount;
    private int curApplicantCount;
}
