package com.bb.focus.db.entity.admin;

import com.sun.istack.NotNull;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "service_notices")
public class ServiceNotice {

    @Id
    @GeneratedValue
    @Column(name = "service_notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_admin_id")
    private ServiceAdmin serviceAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_notice_category_id")
    private ServiceNoticeCategory category;

    @NotNull
    @Column(length = 300)
    private String title;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @Column(length = 5000)
    private String content;

}
