package com.bb.focus.db.entity.helper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "evaluators_interview_rooms")
@Getter
@Setter
//@NoArgsConstructor
@Table(name = "evaluators_interview_rooms")
public class EvaluatorInterviewRoom {

    @Id
    @GeneratedValue
    @Column(name = "evaluator_interview_room_id")
    private Long id;

    private Long evaluatorId;

    private Long interviewRoomId;
}
