package com.bb.focus.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Rooms {
    @Id
    @GeneratedValue
    private  int roomId;

    @ManyToOne
    @JoinColumn(name="interview_id")
    private Interview interviewId;

    private String startTime;
    private String endTime;
    private String link;
}
