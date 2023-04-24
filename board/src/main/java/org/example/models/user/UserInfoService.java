package org.example.models.user;

import lombok.RequiredArgsConstructor;
import org.example.entities.Users;
import org.example.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {

    private final UsersRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 세션에서 항상정보를 넘긴다?
        Users user = repository.findByUserId(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }

        return UserInfo.builder()
                .userNo(user.getUserNO())
                .userId(user.getUserId())
                .userPw(user.getUserPw())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .build();
    }



}
