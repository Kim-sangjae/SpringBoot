package org.example.commons.rest;

import org.springframework.http.HttpStatus;

public class RestCommonException extends RuntimeException{

    private HttpStatus status;

    public RestCommonException(String message){
        this(message,HttpStatus.INTERNAL_SERVER_ERROR); // 상태코드를 입력하지 않은 경우 500 상태 코드 고정
    }

    public RestCommonException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }



}
