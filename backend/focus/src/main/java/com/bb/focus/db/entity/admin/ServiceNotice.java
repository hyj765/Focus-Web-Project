package com.bb.focus.db.entity.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServiceNotice {

    @Id
    @GeneratedValue
    @Column(name = "service_notice_id")
    private Long id;

    private Long serviceAdminId;

    @Column(name = "service_notice_category_id")
    private Long categoryId;

    private String title;
    private LocalDateTime createdAt;
    private String content;

}
