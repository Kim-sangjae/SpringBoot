package org.example.config;

import org.example.models.user.UserInfo;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> { // 회원 Id로 할거기 때문에  <String>

    @Override
    public Optional<String> getCurrentAuditor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // principal 은 null값이 아니다 구현체가 -> String : 비회원(Anonymous)
        //                                      -> UserDetails 구현체
        String userId = null;
        if(principal instanceof UserInfo){
            UserInfo user = (UserInfo) principal;
            userId = user.getUserId();
        }

        return Optional.ofNullable(userId);
    }
}
