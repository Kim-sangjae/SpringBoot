package org.koreait.tests;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.koreait.contants.MemberType;
import org.koreait.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class JPAExam02Test {

    @Autowired
    private EntityManager em;


    @Test
    public void exam01(){
        Member member = new Member();

        member.setUserId("user01");
        member.setUserPw("123456");
        member.setUserNm("사용자01");
        member.setMobile("01012341234");
        member.setType(MemberType.USER);

        em.persist(member);
        em.flush();

        em.detach(member);
        Member member1 = em.find(Member.class,member.getUserNo());
        System.out.println(member1);
    }









}
