package com.tenco.blog_v2.User;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_tb")
@Builder
@AllArgsConstructor
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    // "USER", "ADMIN";
    @Column(nullable = true)
    private String role;

    @CreationTimestamp // 엔티티 생성시 자동으로 현재 시간 입력
    private Timestamp createdAt;



}
