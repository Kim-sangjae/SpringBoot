package org.example.models.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
public class UserInfo implements UserDetails {

    private Long userNo;
    private String userId;
    private String userNm;
    private String userPw;
    private String email;
    private String mobile;

    private Collection<GrantedAuthority> authorities; // 사용권한?



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPw;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // 계정이 만료되었는지

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { //비활성화 - 탈퇴한회원관리?
        return true;
    } //탈퇴 false면 탈퇴한 회원
}
