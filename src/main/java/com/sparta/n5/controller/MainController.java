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
    @GetMapping("/comment")
    public void saveComment(Comment comment){
        commentService.write(comment);
    }

    /*  TODO: 개인 페이지 별 호출할 예정 -> 각자 이름 작성 해주세요*/

    // 개인 페이지
    @GetMapping("/m2bi")
    public String m2biPage(Model model){

        List<Comment> comments = commentService.commentsList("m2bi");
        model.addAttribute("list", comments);

        return "m2bi-page";
    }


    // 개인 페이지
    @GetMapping("/seok")
    public String seokPage(Model model){

        List<Comment> comments = commentService.commentsList("seok");
        model.addAttribute("list", comments);

        return "seok-page";
    }

    // 개인 페이지
    @GetMapping("/c1")
    public String c1Page(Model model){

        List<Comment> comments = commentService.commentsList("c1");
        model.addAttribute("list", comments);

        return "c1-page";
    }

    // 개인 페이지
    @GetMapping("/rinyejo")
    public String rinyejoPage(Model model){

        List<Comment> comments = commentService.commentsList("rinyejo");
        model.addAttribute("list", comments);

        return "rinyejo-page";
    }

    // 개인 페이지
    @GetMapping("/yun")
    public String yunPage(Model model){

        List<Comment> comments = commentService.commentsList("yun");
        model.addAttribute("list", comments);

        return "yun-page";
    }


    //글 삭제
    @GetMapping("n5/delete")
    public String deleteComment(Long id){

//        commentService.commentDelete(id);
        return "redirect:/n5/list";
    }

    //수정할때 해당 글 불러오기용
    @GetMapping("/n5/view")
    public String commentsView(Model model,Long id){
//        model.addAttribute("n5",commentService.commentView(id));
        return "n5view";
    }

    //글 수정
    @GetMapping("/n5/modify/{id}")
    public String updateComment(@PathVariable("id") Long id, Model model){

//        model.addAttribute("n5",commentService.commentView(id));

        return "n5modify";
    }

    @PostMapping("/n5/update/{id}")
    public String updateComment(@PathVariable("id") Long id, Comment comment){

        //n5Temp에 수정전에 있던 내용을 n5로 n5Temp에 수정한 내용 변경하기
//        Comment commentTemp =commentService.commentView(id);
//        commentTemp.setName(comment.getName());
//        commentTemp.setContent(comment.getContent());
//
//        commentService.write(commentTemp);

        return "redirect:/n5/list";
    }

}
