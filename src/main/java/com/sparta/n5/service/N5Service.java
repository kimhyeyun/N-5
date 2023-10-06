package com.sparta.n5.service;

import com.sparta.n5.entity.N5;
import com.sparta.n5.repository.N5Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class N5Service {
    @Autowired
    private N5Repository n5Repository;

    //글 작성
    public void write(N5 n5){
        n5Repository.save(n5);
    }

    //게시글 불러오기
    public List<N5> n5List(){
        return n5Repository.findAll();
    }

    //게시글 삭제
    public void n5Delete(Long id){
        n5Repository.deleteById(id);
    }

    //특정 게시글 불러오기
    public N5 n5View(Long id){
        return n5Repository.findById(id).get();
    }


}
