package org.koreait.entities;
import jakarta.persistence.*;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.koreait.contants.MemberType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users",indexes ={ @Index(name="idx_userNm",columnList = "userNm"),
                                @Index(name="idx_regDt_desc", columnList = "regDt DESC")
})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long userNo;

    @Column(length = 45, unique = true, nullable = false)
    private String userId;
    @Column(length = 65, nullable = false)
    private String userPw;

    @Column( length = 40,nullable = false)
    private String userNm;

//    @Transient // 엔티티 상태에서 제외시킴 ( 디비 , 상태반영 모두 제외 )
    @Column(name = "cellPhone",length = 11)
    private  String mobile;

    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private MemberType type;



    @Temporal(TemporalType.DATE)
    @CreationTimestamp // 현재날짜 자동추가
    private LocalDate regDt;

    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private LocalDate modDt;





}
