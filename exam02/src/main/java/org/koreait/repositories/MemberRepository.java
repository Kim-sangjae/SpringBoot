package org.koreait.repositories;

import com.querydsl.core.BooleanBuilder;
import org.koreait.entities.Member;
import org.koreait.entities.QMember;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long>, QuerydslPredicateExecutor { // <엔티티 클래스 , PK의 기본자료형>

    Member findByUserId(String userId);

    List<Member> findByUserIdNot(String userId); // userId <> ...

    List<Member> findByUserNmContaining(String key); // userNm Like '%:key%'

    List<Member> findByRegDtBetween(LocalDate sDate, LocalDate eDate);

    List<Member> findByRegDtBetween(LocalDate sDate, LocalDate eDate, Pageable pageable);

    List<Member> findByRegDtBetweenOrderByRegDtDesc(LocalDate sDate, LocalDate eDate);

    @Query("select m from Member m where m.userNm like %:key% order by m.regDt desc")
    List<Member> findByUsers(@Param("key") String keyword);



    default  List<Member> findUsers(String keyword){
        QMember member = QMember.member;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member.userNm.contains(keyword))
                .and(member.userId.notIn("user1","user2"));

        List<Member> members = (List)findAll(builder);

        return members;
    }






}
