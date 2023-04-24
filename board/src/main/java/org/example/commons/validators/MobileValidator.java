package org.example.commons.validators;

public interface MobileValidator {


    default boolean mobileCheck(String mobile){

        // 1. 형식을 통일시킨다(숫자만 남긴다..)
        // 2. 형식을 체크

        mobile = mobile.replaceAll("\\D","");

        String pattern = "^01[016]\\d{3,4}\\d{4}$";



        return mobile.matches(pattern); // 적립표현식으로 패턴을 체크하는 메서드
    }



}
