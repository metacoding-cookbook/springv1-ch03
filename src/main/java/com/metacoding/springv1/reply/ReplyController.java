package com.metacoding.springv1.reply;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.metacoding.springv1.user.User;
import com.metacoding.springv1.core.handler.ex.Exception401;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/replies/save")
    public String save(ReplyRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("session");
        if(sessionUser == null){
            throw new Exception401("로그인이 필요합니다.");
        }
        replyService.댓글쓰기(requestDTO, sessionUser, requestDTO.getBoardId());
        return "redirect:/boards/" + requestDTO.getBoardId();
    }
    @PostMapping("/replies/{id}/delete")
    public String deleteById(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("session");
        if(sessionUser == null){
            throw new Exception401("로그인이 필요합니다.");
        }
        int boardId = replyService.댓글삭제(id, sessionUser.getId());
        return "redirect:/boards/" + boardId;
    }
}