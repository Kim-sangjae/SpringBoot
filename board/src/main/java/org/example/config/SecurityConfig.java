package org.example.config;

import org.example.models.user.LoginFailureHandler;
import org.example.models.user.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/user/login") // 로그인 페이지 URL
                //.defaultSuccessUrl("/") // 성공시 이동할 URL
                .successHandler(new LoginSuccessHandler())
                .usernameParameter("userId")
                .passwordParameter("userPw")
                .failureHandler(new LoginFailureHandler())
                //.failureUrl("/user/login") // 로그인 실패시 이동할 URL
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/login"); // 로그아웃 성공시 이동할 URL


        // 특정페이지 권한주기
        http.authorizeHttpRequests()
                .requestMatchers("/mypage/**").authenticated() // 로그인한 회원만 가능한 URL 패턴
                .requestMatchers("/admin/**").hasAuthority("ADMIN") // 관리자만 접근 가능한 URL 패턴
                .anyRequest().permitAll();

        // 관리자 접근 권한 없을시 -> 접근 권한이 없습니다 . 401 -Unauthorized
        // 비회원 접근 권한 없는경우 -> 로그인 페이지

        http.exceptionHandling()
                .authenticationEntryPoint((req,res,e)->{
                    String redirectUrl = "/user/login";
                    String URI = req.getRequestURI();
                    if(URI.indexOf("/admin") != -1){ // 관리자 페이지
                        redirectUrl = "/error/401";
                    }


                    res.sendRedirect(redirectUrl);
                });


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){

        return web -> web.ignoring()
                .requestMatchers("/css/**","/js/**","/images/**","/api/**");
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
