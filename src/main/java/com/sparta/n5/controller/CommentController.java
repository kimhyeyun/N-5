package com.sparta.n5.controller;

import com.sparta.n5.entity.Comment;
import com.sparta.n5.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    //내용 입력하는 처음 화면
    @GetMapping("/n5/") //localhost:8080/
    public String commentWriteForm(){
        return "comment-write";
    }

    //데이터 베이스에 내용 저장
    @PostMapping("/member/{memberName}/")
    public String saveComment(Comment comment){

        commentService.write(comment);

        return "";
    }

    //글 리스트들 가져오기
    @GetMapping("/n5/list")
    public String commentList(Model model){

        model.addAttribute("list",commentService.commentsList());

        return "n5list";
    }

    //글 삭제
    @GetMapping("n5/delete")
    public String deleteComment(Long id){

        commentService.commentDelete(id);
        return "redirect:/n5/list";
    }

    //수정할때 해당 글 불러오기용
    @GetMapping("/n5/view")
    public String commentsView(Model model,Long id){
        model.addAttribute("n5",commentService.commentView(id));
        return "n5view";
    }

    //글 수정
    @GetMapping("/n5/modify/{id}")
    public String updateComment(@PathVariable("id") Long id, Model model){

        model.addAttribute("n5",commentService.commentView(id));

        return "n5modify";
    }

    @PostMapping("/n5/update/{id}")
    public String updateComment(@PathVariable("id") Long id, Comment comment){

        //n5Temp에 수정전에 있던 내용을 n5로 n5Temp에 수정한 내용 변경하기
        Comment commentTemp =commentService.commentView(id);
        commentTemp.setName(comment.getName());
        commentTemp.setContent(comment.getContent());

        commentService.write(commentTemp);

        return "redirect:/n5/list";
    }

}
