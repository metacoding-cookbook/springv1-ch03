package com.metacoding.springv1.board;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp; // 추가

import com.metacoding.springv1.reply.Reply; // 추가
import com.metacoding.springv1.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity  
@Table(name="board_tb") 
public class Board {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)  
    private Integer id;
    private  String title;
    private  String content;

    @CreationTimestamp 
    private  Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "user_id") 
    private User user; 

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY) // reply 필드 연결
    private List<Reply> replies = new ArrayList<>();; // List가 없으면 null이 아닌 0 반환

    @Builder
    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}