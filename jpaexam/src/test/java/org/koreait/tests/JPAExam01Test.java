package org.koreait.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class JPAExam01Test {

    @Autowired
    private EntityManager em;


    @Test
    @DisplayName("엔티티 테이블 생성 테스트")
    public void test01(){
        Member member = new Member();
        member.setUserNo(1L);
        member.setUserId("user01");
        member.setUserPw("123456789");
        member.setUserNm("사용자01");

        em.persist(member); // 상태변화 감지 상태 -> member : 영속성 관리 상태
        em.flush(); // DB에 상태변경사항 반영 insert

        em.detach(member); // 상태변화 감지 분리

        member.setUserNm("(수정)사용자01"); // 상태가 변경 되었다
        em.flush(); // 상태에따라 쿼리가 실행 update

        em.merge(member); // 분리상태에서 -> 다시 상태변화감지 상태로 변경

        Member member2 = em.find(Member.class,member.getUserNo());
        System.out.println(member2);


        //em.remove(member);
        //em.flush(); // delete 쿼리 실행


        ///////////////////////////////
        // JPQL 방식으로 좀 더 세밀하게 작업할 수 도 있다
        String sql = "SELECT m FROM Member m WHERE userNo = :userNo";
        TypedQuery<Member> typedQuery = em.createQuery(sql,Member.class);
        typedQuery.setParameter("userNo", member.getUserNo());

        List<Member> members = typedQuery.getResultList();
        System.out.println(members);

        members.get(0).setUserNm("(수정2)사용자01");
        em.flush();


    }






}
