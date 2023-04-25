package org.example.restcontrollers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.example.commons.rest.JSONResult;
import org.example.commons.rest.RestCommonException;
import org.example.controllers.users.JoinForm;
import org.example.controllers.users.JoinValidator;
import org.example.entities.Users;
import org.example.models.user.UserSaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Log
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ApiUserController {

    private final UserSaveService service;
    private final JoinValidator validator;

    @PostMapping("/account")
    public ResponseEntity<Object> account(@RequestBody @Valid JoinForm joinForm, Errors errors){

        validator.validate(joinForm, errors);

        ResourceBundle bundle = ResourceBundle.getBundle("messages.validations");

        if(errors.hasErrors()){

            List<String> errMessages = errors.getAllErrors().stream().map(e->{

                List<String> messages = new ArrayList<>();
                String[] codes = e.getCodes();

              for(String code : codes){
                  String msg = null;
                  try {
                     msg = bundle.getString(code);
                  }catch (Exception e2){
//                      messages.add(code);
                      msg = e.getDefaultMessage();
                  }

                  if(msg != null && !msg.isBlank()){
                      messages.add(msg);
                  }
              }


              return messages.stream().collect(Collectors.joining(","));
            }).toList();

            String errMessage = errMessages.stream().collect(Collectors.joining(","));

            throw new RestCommonException(errMessage, HttpStatus.BAD_REQUEST);

        } // if



        service.save(joinForm);


        log.info(joinForm.toString());

        return ResponseEntity.ok().build(); // 성공시는 응답코드 200, body 데이터 x
    }









}
