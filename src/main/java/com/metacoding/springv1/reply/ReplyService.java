package com.metacoding.springv1.reply;

import org.springframework.stereotype.Service;

import com.metacoding.springv1.board.BoardRepository;
import com.metacoding.springv1.user.User;
import com.metacoding.springv1.board.Board;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import com.metacoding.springv1.core.handler.ex.Exception404;
import com.metacoding.springv1.core.handler.ex.Exception403;


@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void 댓글쓰기(ReplyRequest.SaveDTO requestDTO, User sessionUser, Integer boardId) {
        Board board = boardRepository.findByIdJoinUser(boardId).orElseThrow(()->new Exception404("게시글을 찾을 수 없습니다."));
        Reply reply = requestDTO.toEntity(sessionUser, board);
        replyRepository.save(reply);
    }
    @Transactional
    public int 댓글삭제(Integer replyId, Integer sessionUserId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(()->new Exception404("댓글을 찾을 수 없습니다."));
        if(reply.getUser().getId() != sessionUserId) {
            throw new Exception403("댓글을 삭제할 권한이 없습니다.");
        }
        replyRepository.deleteById(replyId);
        return reply.getBoard().getId();
    }
}
