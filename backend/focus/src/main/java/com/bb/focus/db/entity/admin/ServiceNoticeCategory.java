package com.bb.focus.db.entity.admin;

import com.sun.istack.NotNull;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "service_notice_categories")
public class ServiceNoticeCategory {

    @Id
    @GeneratedValue
    @Column(name = "service_notice_category_id")
    private Long id;

    @NotNull
    @Column(length = 30)
    private String name;
}
