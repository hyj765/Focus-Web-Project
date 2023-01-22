package com.bb.focus.db.entity.authorization;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tokens")
public class Token {

    @Id
    private String userId;

    @NotNull
    @Column(length = 1000)
    private String refreshTokens;

    @NotNull
    private LocalDateTime expireDate;

}
