package org.koreait.controllers;

import com.querydsl.jpa.JPAQueryBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.entities.BoardData;
import org.koreait.entities.Member;
import org.koreait.entities.MemberAddress;
import org.koreait.entities.QBoardData;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.MemberAddressRepository;
import org.koreait.repositories.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
@Log
@RestController
@RequiredArgsConstructor
public class Exam01Controller {

    private final BoardDataRepository boardDataRepository;
    private final MemberRepository memberRepository;
    private final EntityManager em;
    private final MemberAddressRepository memberAddressRepository;


    @GetMapping("/ex01")
    public void ex01() {
        Member member = Member.builder()
                .userId("user01")
                .userPw("123456")
                .userNm("사용자01")
                .mobile("01000000000")
                .email("user01@user.org")
                .build();

        member = memberRepository.saveAndFlush(member);

        List<BoardData> datas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            BoardData data = BoardData.builder()
                    .subject("제목" + i)
                    .content("내용" + i)
                    .member(member)
                    .build();
            datas.add(data);
        }

        boardDataRepository.saveAllAndFlush(datas);
    }

    @GetMapping("/ex02")
    public void ex02() {
        BoardData data = boardDataRepository.findById(1L).orElse(null); // BoarData 엔티티만 조회
//        log.info(data.toString());
//        Member member = data.getMember();
        Member member = data.getMember();
        String userId = member.getUserId(); // 쿼리실행
        log.info(userId);
//        log.info(member.toString());
    }


    @GetMapping("/ex03")
    public void ex03() {
        Member member = memberRepository.findById(1L).orElse(null);
        List<BoardData> boardDatas = member.getBoardData();

        boardDatas.stream().forEach(System.out::println);
    }


    @GetMapping("/ex04")
    public void ex04(){
        List<BoardData> boardDatas = boardDataRepository.findAll();
        for(BoardData boardData : boardDatas){
            Member member = boardData.getMember();
            String userId = member.getUserId();
            String userNm = member.getUserNm();

            log.info("userId=" + userId + "userNm" + userNm);
        }
    }



    @GetMapping("/ex05")
    public void ex05(){
        List<BoardData> boardDatas = boardDataRepository.findBoardDatas();

    }




    @GetMapping("/ex06")
    @Transactional // 엔티티매니저를 쓰려면 이 애노테이션이 필요
    public void ex06(){

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QBoardData boardData = QBoardData.boardData;

        JPAQuery<BoardData> query = queryFactory.selectFrom(boardData)
                                                .leftJoin(boardData.member)
                                                .orderBy(boardData.regDt.desc())
                                                .fetchJoin();

        List<BoardData> datas = query.fetch();
        datas.stream().forEach(System.out::println);


    }


    @GetMapping("/ex07")
    public void ex07(){
        MemberAddress address = MemberAddress.builder()
                .addr1("주소1")
                .addr2("상세주소1")
                .zipCode("14029")
                .build();

        address = memberAddressRepository.saveAndFlush(address);


        Member member = Member.builder()
                .userId("user02")
                .userPw("1234567")
                .userNm("사용자02")
                .address(address)
                .email("dldldl@bobo.com")
                .mobile("1919191919")
                .build();

        memberRepository.save(member);
    }


    @GetMapping("/ex08")
    public void ex08(){
        Member member = memberRepository.findById(2L).orElse(null);
        MemberAddress address = member.getAddress();
        log.info(address.toString());
    }



    @GetMapping("/ex09")
    public void ex09(){
        MemberAddress address = memberAddressRepository.findById(1L).orElse(null);
        Member member = address.getMember();

        log.info(member.toString());
    }


    @GetMapping("/ex10")
    public void ex10(){
        Member member = memberRepository.findById(1L).orElse(null);
        memberRepository.delete(member);
        memberRepository.flush();
    }





}