package org.example.models.user;

import lombok.RequiredArgsConstructor;
import org.example.controllers.users.JoinForm;
import org.example.entities.Users;
import org.example.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSaveService {


    private final UsersRepository repository;

    private final PasswordEncoder encoder;




    public void save(JoinForm joinForm){

        Users user = JoinForm.of(joinForm);

        String hash = encoder.encode(joinForm.getUserPw());
        user.setUserPw(hash);

        repository.saveAndFlush(user);


    }



}
