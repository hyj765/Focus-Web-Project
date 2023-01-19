package com.bb.focus.db.entity.interview;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rooms_chatting")
@Getter
@Setter
@NoArgsConstructor
public class RoomChatting {

    @Id
    @GeneratedValue
    @Column(name = "room_chatting_id")
    private Long id;

    private String senderId;

    private String message;
}
