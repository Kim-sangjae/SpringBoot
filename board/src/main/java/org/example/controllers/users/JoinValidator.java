package org.example.controllers.users;

import lombok.RequiredArgsConstructor;
import org.example.commons.validators.MobileValidator;
import org.example.repositories.UsersRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator , MobileValidator {

    private final UsersRepository repository;




    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz); // 커맨드객체를 JoinForm 으로 한정하겠다
    }




    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors()){ // Bean Validation 에 검증 실패가 있는경우
            return;
        }

        JoinForm joinForm = (JoinForm) target;
        String userId = joinForm.getUserId();
        String userPw = joinForm.getUserPw();
        String userPwRe = joinForm.getUserPwRe();
        String mobile = joinForm.getMobile();

        // 1. 아이디 중복여부

        if(repository.userExists(userId)){
            errors.rejectValue("userId","Validation.duplicate.userId");
        }


        // 2. userPw,userPwRe 일치여부
        if(!userPw.equals(userPwRe)){
            errors.rejectValue("userPwRe","Validation.incorrect.userPw");
        }


        // 3. 휴대전화번호(선택사항)가 있으면 형식 체크

        if(mobile!=null && !mobile.isBlank()){ // 값이있을 때만 체크 (선택사항이니까)
            if(!mobileCheck(mobile)){ // 휴대전화번호 형식이 아닌경우
                errors.rejectValue("mobile","Validation.mobile");
            }

            mobile = mobile.replaceAll("\\D","");
            joinForm.setMobile(mobile);

        }




    }




}
