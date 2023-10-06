package com.sparta.n5.service;

import com.sparta.n5.entity.Comment;
import com.sparta.n5.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    //글 작성
    public void write(Comment comment){
        commentRepository.save(comment);
    }

    //게시글 불러오기
    public List<Comment> commentsList(){
        return commentRepository.findAll();
    }

    //게시글 삭제
    public void commentDelete(Long id){
        commentRepository.deleteById(id);
    }

    //특정 게시글 불러오기
    public Comment commentView(Long id){
        return commentRepository.findById(id).get();
    }


}
