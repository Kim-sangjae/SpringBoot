package org.example.controllers.mypage;

import lombok.extern.java.Log;
import org.apache.catalina.User;
import org.example.entities.BoardData;
import org.example.models.user.UserInfo;
import org.example.repositories.BoardDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/mypage")
@Log
public class MypageController {
    @Autowired
    private BoardDataRepository repository;

    //방법1
//    @GetMapping // 마이페이지 메인
//    public String index(Principal principal){
//
//        String userId = principal.getName(); // 현재 로그인 한 username -> 아이디
//        log.info(userId);
//
//        return "mypage/index";
//    }



    //방법2
    @GetMapping
    public String index(@AuthenticationPrincipal UserInfo userInfo){ // 로그인한 회원의 전체 정보
        log.info(userInfo.toString());

        return "mypage/index";
    }


    //방법3
//    @GetMapping
//    public String index(){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        UserInfo userInfo = (UserInfo)principal;
//
//        log.info(userInfo.toString());
//        return "mypage/index";
//    }



    @GetMapping("/exam")
    @ResponseBody
    public void exam(){
        BoardData data = BoardData.builder()
                .subject("제목")
                .content("내용")
                .build();

        repository.saveAndFlush(data);

        data.setSubject("(수정)제목");
        repository.flush();
    }






}
