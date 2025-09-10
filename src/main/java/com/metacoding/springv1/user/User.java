package com.metacoding.springv1.user;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity  
@Table(name="user_tb") 
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)  // 자동 증가
    private Integer id;
    private  String username;
    private  String password;
    private  String email;

    @CreationTimestamp // 자동으로 현재 시간 저장
    private  Timestamp createdAt;

    // 객체 생성을 위한 생성자
    @Builder
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}