package org.koreait.entities;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member extends BaseEntity{
    @Id
    @GeneratedValue
    private Long userNo;

    @Column(length = 40 , unique = true, nullable = false)
    private String userId;
    @Column(length = 65 , nullable = false)
    private String userPw;
    @Column(length=40, nullable = false)
    private String userNm;



    //mappedBy = BoardData엔티티에 외래키 이름으로 설정된 변수명
    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY) // 연관관계의 주인을 명시해야한다 (서로 조인상태)
    private List<BoardData> boardData = new ArrayList<>();


    @OneToOne
    @JoinColumn(name="addressId")
    @ToString.Exclude
    private Address address;




}
