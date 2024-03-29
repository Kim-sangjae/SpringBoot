package org.example.controllers.users;

import jakarta.validation.Valid;
import org.example.models.user.UserSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
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

    @GetMapping("/join")
    public String join(Model model) {

        JoinForm joinForm = new JoinForm();
        model.addAttribute("joinForm", joinForm);

        return "user/join";
    }

    @PostMapping
    public String joinPs(@Valid JoinForm joinForm, Errors errors) {

        validator.validate(joinForm, errors);

        if (errors.hasErrors()) {
            return "user/join";
        }

        service.save(joinForm);

        return "redirect:/user/login"; // 회원 가입 완료 후 -> 로그인 이동
    }

    @GetMapping("/login")
    public String login(@CookieValue(required = false) String saveId, Model model) {
        if (saveId != null) {
            model.addAttribute("userId", saveId);
            model.addAttribute("saveId", saveId);
        }

        return "user/login";
    }
}