package com.sparta.n5.service;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.sparta.n5.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommentService {

    //글 작성
    public void write(Comment comment){
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(comment.getMemberName()).document(comment.getName()).set(comment);
    }

    //게시글 불러오기
    public List<Comment> commentsList(String name) {
        Comment comment = null;
        List<Comment> list = new ArrayList<>();

        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collection = firestore.collection(name);

        Iterable<DocumentReference> documentReferences = collection.listDocuments();
        for (DocumentReference d : documentReferences) {
            try {
                DocumentSnapshot apiFuture = d.get().get();
                comment = apiFuture.toObject(Comment.class);
                list.add(comment);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    public void commentDelete(String memberName, String name) {
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(memberName).document(name).delete();
    }

    public void update(Comment comment) {
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(comment.getMemberName()).document(comment.getName()).update("content", comment.getContent());
    }
}
