package org.koreait.repositories;

import org.koreait.entities.Member;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> { // <엔티티 클래스 , PK의 기본자료형>

    Member findByUserId(String userId);

    List<Member> findByUserIdNot(String userId); // userId <> ...

    List<Member> findByUserNmContaining(String key); // userNm Like '%:key%'

    List<Member> findByRegDtBetween(LocalDate sDate, LocalDate eDate);

    List<Member> findByRegDtBetween(LocalDate sDate, LocalDate eDate, Pageable pageable);

    List<Member> findByRegDtBetweenOrderByRegDtDesc(LocalDate sDate, LocalDate eDate);

    @Query("select m from Member m where m.userNm like %:key% order by m.regDt desc")
    List<Member> findByUsers(@Param("key") String keyword);


}
