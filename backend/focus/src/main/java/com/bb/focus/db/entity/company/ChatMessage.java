package com.bb.focus.db.entity.company;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "chat_messages")
@Getter
@Setter
//@NoArgsConstructor
@Table(name = "chat_messages")
public class ChatMessage {

  @Id
  @GeneratedValue
  private int chatMessageId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="chat__Room_id")
  private ChatRoom chat;



}
