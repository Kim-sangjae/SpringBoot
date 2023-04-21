package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity @Builder // 기본적으로 빌더틑 생성자가 막혀있다
@NoArgsConstructor @AllArgsConstructor // 편법
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long userNo;

    @Column(length = 45,nullable = false,unique = true)
    private String userId;
    @Column(length = 65,nullable = false)
    private String userPw;
    @Column(length = 45,nullable = false)
    private String userNm;

    @Column(length = 100)
    private String email;
    @Column(length = 11)
    private String mobile;


    @OneToMany(mappedBy = "member") // 관계의 주인을 정한다  BoardData 에 있는 "member" 가 주인이다(외래키)
    private List<BoardData> boardData = new ArrayList<>();


    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private MemberAddress address;



}
