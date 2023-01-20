package com.bb.focus.db.entity.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "service_notice_categories")
@Getter
@Setter
//@NoArgsConstructor
@Table(name = "service_notice_categories")
public class ServiceNoticeCategory {

    @Id
    @GeneratedValue
    @Column(name = "service_notice_category_id")
    private Long id;

    private String name;
}
