package com.bb.focus.db.entity.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "faqs")
@Getter
@Setter
//@NoArgsConstructor
@Table(name = "faqs")
public class Faq {

    @Id
    @GeneratedValue
    @Column(name = "faq_id")
    private Long id;

    private Long serviceAdminId;

    private String title;
    private LocalDateTime createdAt;
    private String content;
    private int range;  //3:기업관리자,평가자 열람 가능, 4:기업관리자,지원자 열람 가능


}
