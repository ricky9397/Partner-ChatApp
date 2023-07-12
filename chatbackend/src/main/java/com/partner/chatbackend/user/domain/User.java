package com.partner.chatbackend.user.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_USERS")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_EMAIL", unique = true)
    private String userEmail;

    @NotNull
    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;

    @NotNull
    @Column(name = "USER_PHONE")
    private String userPhone;

    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(foreignKey = @ForeignKey(name = "userId"))
    //private Set<Authority> authorities;

    @ColumnDefault(value = "0")
    @Column(name = "LOGIN_FAIL_CNT")
    private int loginFailCnt;

    @NotNull
    @Column(name = "USER_YN", length = 1)
    private String userYn;

    @ColumnDefault(value = "'N'")
    @Column(name = "LOCKED_YN")
    private String lockedYn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REG_DATE")
    private LocalDateTime regDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MOD_DATE")
    private LocalDateTime modDate;

//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime loginDate;

    @ColumnDefault(value = "'Y'")
    @Column(name = "USE_YN")
    private String useYn;

    @ColumnDefault(value = "'ROLE_USER'")
    @Column(name = "ROLE")
    private String role;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "PROVIDER_ID", length = 2000)
    private String providerId;

    @Column(name = "PROVIDER")
    private String provider;
}
