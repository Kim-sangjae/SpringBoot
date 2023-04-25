package org.example.controllers.commons;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
//@RequiredArgsConstructor
public class ErrorsController {

    @Autowired
    private HttpServletResponse response;


    @RequestMapping("/401")
    public String error401(){

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        return "error/401";
    }









}
