package com.sparta.n5.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.sparta.n5.entity.Comment;
//import com.sparta.n5.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class CommentService {

    //글 작성
    public void write(){
//        commentRepository.save(comment);

//       test
        Firestore db = FirestoreClient.getFirestore();
        Comment comment1 = new Comment();
        comment1.setId(1L);
        comment1.setName("test");
        comment1.setContent("test");

        ApiFuture<WriteResult> apiFuture = db.collection("movies").document("test").set(comment1);

    }

    //게시글 불러오기
    public List<Comment> commentsList() {
        Comment comment = null;
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentSnapshot> document = firestore.collection("movies").document("test").get();
        DocumentSnapshot documentSnapshot = null;
        try {
            documentSnapshot = document.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (documentSnapshot.exists()) {
            comment = documentSnapshot.toObject(Comment.class);
        }

        List<Comment> list = new ArrayList<>();
        list.add(comment);

        System.out.println(comment.toString());
        return list;
//        return commentRepository.findAll();
    }

}
