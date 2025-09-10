package com.metacoding.springv1.reply;

import com.metacoding.springv1.board.Board;
import com.metacoding.springv1.user.User;

import lombok.Data;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private String comment;
        private Integer boardId;

        public Reply toEntity(User user, Board board) {
            return Reply.builder()
                        .comment(comment)
                        .user(user)
                        .board(board)
                        .build();
        }
    }
}