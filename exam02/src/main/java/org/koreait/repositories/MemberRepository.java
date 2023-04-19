package org.koreait.repositories;

import org.koreait.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> { // <엔티티 클래스 , PK의 기본자료형>

    Member findByUserId(String userId);

}
