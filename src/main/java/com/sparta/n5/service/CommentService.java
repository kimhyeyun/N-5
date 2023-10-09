package com.sparta.n5.service;

import com.sparta.n5.entity.Comment;
import com.sparta.n5.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentService {

    @Autowired CommentRepository commentRepository;
    //글 작성
    public void write(Comment comment){
        commentRepository.save(comment);
    }

    //게시글 불러오기
    public List<Comment> commentsList(String name) {
        return commentRepository.getCommentsByMemberName(name);
    }

    public String commentDelete(Long id) {
        Comment comment = commentRepository.getReferenceById(id);
        commentRepository.deleteById(id);

        return comment.getMemberName();
    }
}
