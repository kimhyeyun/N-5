package com.sparta.n5.controller;

import com.sparta.n5.entity.Comment;
import com.sparta.n5.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired private CommentService commentService;

    //내용 입력하는 처음 화면
    @GetMapping("/") //localhost:8080/
    public String index(){
        return "index";
    }

    @GetMapping("/home") //localhost:8080/
    public String home(){
        return "home";
    }
    @GetMapping("/member2") //localhost:8080/
    public String member(){
        return "member2";
    }
    //데이터 베이스에 내용 저장
    @GetMapping("/q") //localhost:8080/
    public String q(){
        return "q";
    }
    //데이터 베이스에 내용 저장



    @PostMapping("/comment")
    public String saveComment(Comment comment) {
        commentService.write(comment);
        return "redirect:/" + comment.getMemberName();
    }

    // 개인 페이지
    @GetMapping("/{memberName}")
    public String MemberPage(@PathVariable String memberName, Model model){

//        List<Comment> comments = commentService.commentsList(memberName);
//        model.addAttribute("list", comments);

        return memberName + "-page";
    }

    //글 삭제
    @ResponseBody
    @DeleteMapping("/comment")
    public void deleteComment(@RequestBody Comment comment){
        commentService.commentDelete(comment.getMemberName(), comment.getName().substring(1));
    }



    @PostMapping("/comments/update")
    public String updateComment(Comment comment) {
        commentService.update(comment);

        return "redirect:/" + comment.getMemberName();
    }

}
