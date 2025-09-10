package com.metacoding.springv1.board;

import com.metacoding.springv1.user.User;
import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {

        private String title;
        private String content;
        private User user;  // user 필드 추가

        // 빌더 패턴으로 엔티티 생성
	    public Board toEntity(User user) {
	        return Board.builder()
	            .title(title)
	            .content(content)
                .user(user)  
	            .build();
	    }
    }   
    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }
}