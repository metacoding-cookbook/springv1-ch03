package com.metacoding.springv1.board;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpSession;
import com.metacoding.springv1.core.handler.ex.Exception401;
import com.metacoding.springv1.user.User;


@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session; 

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<BoardResponse.DTO> boards = boardService.게시글목록();
        request.setAttribute("models", boards);
        return "index";
    }

    @GetMapping("/boards/{id}")
    public String detail(HttpServletRequest request, @PathVariable Integer id) {
        BoardResponse.DetailDTO board = boardService.게시글상세(id);
        request.setAttribute("model", board);
        return "board/detail";
    }

    @GetMapping("/boards/save-form")
    public String saveForm() {
        User sessionUser = (User)session.getAttribute("session");
        if(sessionUser == null){
            throw new Exception401("로그인이 필요합니다.");
        }
        return "board/save-form";
    }

    @PostMapping("/boards/save")
    public String save(BoardRequest.SaveDTO requestDTO){
        User sessionUser = (User)session.getAttribute("session");
        if(sessionUser == null){
            throw new Exception401("로그인이 필요합니다.");
        }
        boardService.게시글추가(requestDTO,sessionUser);
        return "redirect:/";
    }

    @PostMapping("/boards/{id}/delete")
    public String deleteById(@PathVariable Integer id){
        User sessionUser = (User)session.getAttribute("session"); // 세션 꺼냄
        if(sessionUser == null){
            throw new Exception401("로그인이 필요합니다.");
        }
        boardService.게시글삭제(id,sessionUser.getId());
        return "redirect:/";
    }

    @GetMapping("/boards/{id}/update-form")
    public String updateForm(HttpServletRequest request, @PathVariable Integer id) {
        User sessionUser = (User)session.getAttribute("session");
        if(sessionUser == null){
            throw new Exception401("로그인이 필요합니다.");
        }
        BoardResponse.DTO board = boardService.게시글수정폼(id,sessionUser.getId());
        request.setAttribute("model", board);
        return "board/update-form";
    }

    @PostMapping("/boards/{id}/update")
    public String updateById(@PathVariable Integer id, BoardRequest.UpdateDTO requestDTO){
        User sessionUser = (User)session.getAttribute("session");
        if(sessionUser == null){
            throw new Exception401("로그인이 필요합니다.");
        }
        boardService.게시글수정(id, requestDTO,sessionUser.getId());
        return "redirect:/boards/" + id;
    }
}