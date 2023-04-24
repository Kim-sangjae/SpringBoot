package org.example.controllers.users;

import jakarta.validation.Valid;
import org.example.models.user.UserSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JoinValidator validator;
    @Autowired
    private UserSaveService service;




    @GetMapping
    public String join(Model model){
        JoinForm joinForm = new JoinForm();
        model.addAttribute("joinForm",joinForm);


        return "user/join";
    }



    @PostMapping
    public String joinPs(@Valid JoinForm joinForm, Errors errors){

        validator.validate(joinForm,errors);

        if(errors.hasErrors()){
            return "user/join";
        }


        service.save(joinForm);

        return "redirect:/user/login"; // 양식제출 완료후 로인페이지 이동
    }




}