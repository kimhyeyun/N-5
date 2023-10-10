package com.sparta.n5.controller;

import com.sparta.n5.entity.Comment;
import com.sparta.n5.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired private CommentService commentService;

    //내용 입력하는 처음 화면
    @GetMapping("/") //localhost:8080/
    public String home(){
        return "index";
    }

    //데이터 베이스에 내용 저장
    @PostMapping("/comment")
    public String saveComment(Comment comment) {
        commentService.write(comment);
        return "redirect:/" + comment.getMemberName();
    }

    /*  TODO: 개인 페이지 별 호출할 예정 -> 각자 이름 작성 해주세요*/

    // 개인 페이지

    @GetMapping("/{memberName}")
    public String yunPage(@PathVariable String memberName, Model model){

        List<Comment> comments = commentService.commentsList(memberName);
        model.addAttribute("list", comments);

        return memberName + "-page";
    }


    //글 삭제
    @GetMapping("/comments/{memberName}/{name}")
    public String deleteComment(@PathVariable String memberName, @PathVariable String name){
        commentService.commentDelete(memberName,name);

        return "redirect:/" + memberName;
    }

    @PostMapping("/comments/update")
    public String updateComment(Comment comment) {
        commentService.update(comment);

        return "redirect:/" + comment.getMemberName();
    }

}
