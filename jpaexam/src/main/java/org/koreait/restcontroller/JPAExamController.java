package org.koreait.restcontroller;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.BoardData;
import org.koreait.entities.Member;
import org.koreait.entities.QBoardData;
import org.koreait.entities.Users;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.MemberRepository;
import org.koreait.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jpa")
@Transactional
@RequiredArgsConstructor
public class JPAExamController {

    private final MemberRepository repository;

    private final BoardDataRepository boardDataRepository;

    @Autowired
    private EntityManager em;

    @GetMapping("/ex01")
    public void ex01() {
        Member member = new Member();
        member.setUserId("user01");
        member.setUserPw("123456");
        member.setUserNm("사용자01");

        em.persist(member);
        em.flush();
        for(int i = 1; i <= 10; i++) {
            BoardData boardData = new BoardData();
            boardData.setSubject("제목" + i);
            boardData.setContent("내용" + i);
            boardData.setMember(member);

            em.persist(boardData);
            em.flush();
        }
    }

    @GetMapping("/ex02")
    public void ex02() {
        Member member = em.find(Member.class, 1L);
        List<BoardData> list = member.getBoardData(); // 회원이 작성한 게시글 목록
        list.stream().forEach(System.out::println);
    }

    @GetMapping("/ex03")
    public void ex03() {
        BoardData boardData = em.find(BoardData.class, 1L);
        System.out.println(boardData);

        Member member = boardData.getMember();
        String userId = member.getUserId();
        System.out.println(userId);

    }

    @GetMapping("/ex04")
    public void ex04() {
        String sql = "SELECT b FROM BoardData b LEFT JOIN FETCH b.member WHERE b.id=:id";
        TypedQuery<BoardData> tq = em.createQuery(sql, BoardData.class);
        tq.setParameter("id", 1L);
        BoardData boardData = tq.getSingleResult();
        System.out.println(boardData);
    }

    @GetMapping("/ex05")
    public void ex05() {
        //repository.findByUserNmContaining()
        // flush(), 조회 findAll, findOne -> 실행 전 flush 자동으로 먼저 진행
        List<Member> members = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Member member = new Member();
            member.setUserId("user" + i);
            member.setUserPw("123456");
            member.setUserNm("사용자" + i);
            members.add(member);
        }

        repository.saveAllAndFlush(members);
    }

    @GetMapping("/ex06")
    public void ex06() {
        //Pageable pageable = PageRequest.of(0, 3);
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Order.desc("regDt")));
        List<Member> members = repository.findByUserNmContaining("자", pageable);

        members.stream().forEach(System.out::println);
    }

    @GetMapping("/ex07")
    public void ex07() {
        List<Member> members = repository.findMembers("자");
        members.forEach(System.out::println);
    }

    @GetMapping("/ex08")
    public void ex08() {
        Member member = repository.findById(1L).orElse(null);

        List<BoardData> items = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            BoardData boardData = new BoardData();
            boardData.setSubject("제목" + i);
            boardData.setContent("내용" + i);
            boardData.setMember(member);

            items.add(boardData);
        }

        boardDataRepository.saveAllAndFlush(items);
    }

    @GetMapping("/ex09")
    public void ex09() {
        //BoardData boardData = boardDataRepository.findById(1L).orElse(null);
        //Member member = boardData.getMember();
        //member.getUserId();
        List<BoardData> items = boardDataRepository.findAll();
        for (BoardData item : items) {
            Member member = item.getMember();
            System.out.printf("userId=%s%n", member.getUserId());
        }

    }

    private final UsersRepository uRepository;

    @GetMapping("/ex10")
    public void ex10() {
        Users user = Users.builder()
                .userId("user01")
                .userNm("사용자")
                .build();
        user = uRepository.saveAndFlush(user);

        user.setUserNm("(수정)사용자");
        uRepository.flush();
    }

    @GetMapping("/ex11")
    public void ex11() {
        List<BoardData> datas = boardDataRepository.getBoardData("제목");

    }

    @GetMapping("/ex12")
    public void ex12() {
        QBoardData boardData = QBoardData.boardData;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        JPAQuery<BoardData> jpaQuery = jpaQueryFactory.selectFrom(boardData)
                .leftJoin(boardData.member)
                .fetchJoin();


        List<BoardData> datas = jpaQuery.fetch();
    }



    @GetMapping("/ex13")
    public void ex13() {
        List<BoardData> datas = boardDataRepository.getBoardData2("제목");
    }
}