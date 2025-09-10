package com.metacoding.springv1.board;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import com.metacoding.springv1.reply.Reply;
import java.util.ArrayList;

public class BoardResponse {

    @Data
    public static class DTO {

        private Integer id;
        private String title;
        private String content;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }   

    @Data
    public static class DetailDTO {

        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private List<ReplyDTO> replies = new ArrayList<>();

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.replies = board.getReplies().stream()
                                .map(reply -> new ReplyDTO(reply))
                                .collect(Collectors.toList());
        }
        // List 타입의 댓글을 담는 DTO                                        
        @Data
        public class ReplyDTO {
            private Integer id;
            private String username;
            private String comment;

            public ReplyDTO(Reply reply) {
                this.id = reply.getId();
                this.username = reply.getUser().getUsername();
                this.comment = reply.getComment();
            }
        }
    }
}