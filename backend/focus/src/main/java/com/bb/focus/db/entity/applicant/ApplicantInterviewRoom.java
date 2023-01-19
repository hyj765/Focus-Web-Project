package com.bb.focus.db.entity.applicant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "applicants_interview_rooms")
public class ApplicantInterviewRoom {

    @Id
    @GeneratedValue
    @Column(name = "applicant_interview_room_id")
    private Long id;

    private Long interviewRoomId;
    private Long applicantId;
}
