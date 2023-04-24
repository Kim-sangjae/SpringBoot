package org.example.controllers.users;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.Users;
import org.modelmapper.ModelMapper;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinForm { // 커멘드 객체

    @NotBlank
    @Size(min=6)
    private String userId;
    @NotBlank
    @Size(min=8,max=16)
    private String userPw;
    @NotBlank
    private String userPwRe;
    @NotBlank
    private String userNm;

    @Email
    private String email;
    private String mobile;

    @AssertTrue
    private boolean agree;



    // 변환메서드
    public static Users of(JoinForm joinForm){
//        ModelMapper mapper = new ModelMapper();
//        Users user = mapper.map(joinForm,Users.class);

//        return user;

        return new ModelMapper().map(joinForm,Users.class);
    }



}
