package org.koreait.tests;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class JPAExam01Test {

    @Autowired
    private EntityManager em;

    @Test
    public void exam01(){
        Member member = new Member();
        member.setUserNo(1L);
        member.setUserId("user01");
        member.setUserPw("123456");
        member.setUserNm("사용자01");
        member.setRegDt(LocalDate.now());

        em.persist(member);
        em.flush();

        member.setUserNm("(수정) 사용자01");
        em.flush();

        Member member2 = em.find(Member.class,1L); // 기본키를 가지고 조회할수있는 메서드
        System.out.println(member2);

        em.remove(member);
        em.flush();
    }

    @BeforeEach
    public void insertData(){
        Member member = new Member();
        member.setUserNo(2L);
        member.setUserId("user02");
        member.setUserPw("123456");
        member.setUserNm("사용자02");
        member.setRegDt(LocalDate.now());

        em.persist(member);
        em.flush();
        em.detach(member);
    }



    @Test
    public void exam02(){

        Member member = em.find(Member.class,1L); // select
        System.out.println(member);


        Member member2 = em.find(Member.class,1L); // 한번 더 조회해서 이미 영속성에 존재함, 영속성에서 바로 가져옴
        System.out.println(member2);

    }


    @Test
    public void exam03(){

        Member member = new Member();
        member.setUserNo(1L);
        member.setUserId("user01");
        member.setUserPw("123456");
        member.setUserNm("사용자01");
        member.setRegDt(LocalDate.now());

        em.persist(member);
        em.flush();

        em.detach(member); // 비영속상태 - 상태변화감지 불가능 해짐

        member.setUserNm("(수정) 사용자 01");

        em.flush();

        em.merge(member); // 영속상태 - 상태변화감지가 가능해진다
        em.flush();

    }






}
