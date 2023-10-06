package com.sparta.n5.controller;

import com.sparta.n5.entity.N5;
import com.sparta.n5.service.N5Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class N5Controller {

    @Autowired
    private N5Service n5Service;

    //내용 입력하는 처음 화면
    @GetMapping("/n5/write") //localhost:8080/n5/write
    public String n5WriteFrom(){
        return "N5write";
    }

    //데이터 베이스에 내용 저장
    @PostMapping("/n5/writepro")
    public String N5_Write(N5 n5){

        n5Service.write(n5);

        return "";
    }

    //글 리스트들 가져오기
    @GetMapping("/n5/list")
    public String N5_List(Model model){

        model.addAttribute("list",n5Service.n5List());

        return "n5list";
    }

    //글 삭제
    @GetMapping("n5/delete")
    public String N5_Delete(Long id){

        n5Service.n5Delete(id);
        return "redirect:/n5/list";
    }

    //수정할때 해당 글 불러오기용
    @GetMapping("/n5/view")
    public String n5View(Model model,Long id){
        model.addAttribute("n5",n5Service.n5View(id));
        return "n5view";
    }

    //글 수정
    @GetMapping("/n5/modify/{id}")
    public String N5_Modify(@PathVariable("id") Long id, Model model){

        model.addAttribute("n5",n5Service.n5View(id));

        return "n5modify";
    }

    @PostMapping("/n5/update/{id}")
    public String N5_Update(@PathVariable("id") Long id,N5 n5){

        //n5Temp에 수정전에 있던 내용을 n5로 n5Temp에 수정한 내용 변경하기
        N5 n5Temp =n5Service.n5View(id);
        n5Temp.setName(n5.getName());
        n5Temp.setContent(n5.getContent());

        n5Service.write(n5Temp);

        return "redirect:/n5/list";
    }

}
