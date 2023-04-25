package org.example.restcontrollers;

import org.example.commons.rest.JSONResult;
import org.example.commons.rest.RestCommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("org.example.restcontrollers")
public class CommonRestController {

    @ExceptionHandler(Exception.class) // @ExceptionHandler 공통 예외 관리
    public ResponseEntity<JSONResult<Object>> errorHandler(Exception e){

        JSONResult<Object> jsonResult = new JSONResult<>();
        jsonResult.setSuccess(false);
        jsonResult.setErrorMessage(e.getMessage());


        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 기본값 :500으로
        if(e instanceof  RestControllerAdvice){ // RestCommonException 인 경우는 상태 코드 조회
            RestCommonException restE = (RestCommonException) e;
            status = restE.getStatus();
        }

        return ResponseEntity.status(status).body(jsonResult);
    }
}
