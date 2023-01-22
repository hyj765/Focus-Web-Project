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
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "faqs")
public class Faq {

    @Id
    @GeneratedValue
    @Column(name = "faq_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_admin_id")
    private ServiceAdmin serviceAdmin;

    @NotNull
    @Column(length = 300)
    private String title;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @Column(length=5000)
    private String content;

    @NotNull
    @ColumnDefault("3")
    private Byte authRange;  //3:기업관리자,평가자 열람 가능, 4:기업관리자,지원자 열람 가능


}
