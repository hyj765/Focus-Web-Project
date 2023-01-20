package com.bb.focus.db.entity.interview;

import com.bb.focus.db.entity.company.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "room_chats")
@Table(name = "room_chats")
@Getter
@Setter
//@NoArgsConstructor
public class RoomChat {

    @Id
    @GeneratedValue
    @Column(name = "room_chat_id")
    private Long id;

    private String senderId;

    private String message;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "roomChat")
    private InterviewRoom interviewRoom;
}
