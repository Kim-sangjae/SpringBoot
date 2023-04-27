package org.example.tests;


import org.example.controllers.users.JoinForm;
import org.example.models.user.UserSaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
@AutoConfigureMockMvc
public class ApiUserTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserSaveService service;

    private JoinForm joinForm;

    @BeforeEach
    void init(){
        joinForm = new JoinForm();
        joinForm.setUserId("user06");
        joinForm.setUserPw("12345678");
        joinForm.setUserNm("사용자06");
        joinForm.setUserPwRe("12345678");

        joinForm.setAgree(true);
    }


    @Test
    @DisplayName("회원가입 성공시 200 응답 코드")
    void joinSuccessTest() throws Exception {

        String params = String.format("{ \"userId\":\"%s\", \"userPw\":\"%s\", \"userPwRe\":\"%s\", \"userNm\":\"%s\", \"agree\":\"true\" }"
                ,joinForm.getUserId()
                ,joinForm.getUserPw()
                ,joinForm.getUserPwRe()
                ,joinForm.getUserNm());


        mockMvc.perform(post("/api/user/account")
                        .content(params)
                        .contentType("application/json"))
                .andExpect(status().isOk());


    }



    @Test
    @DisplayName("필수 요청 항목 검증 - 응답 코드 400")
    void joinDataValidation() {

    }







}
