package org.example.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.constants.UserType;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name="idx_userNm",columnList = "userNm"),
        @Index(name="idx_regDt_desc",columnList = "regDt DESC")
})
public class Users extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userNO; // 회원번호

    @Column(length = 40, unique = true, nullable = false)
    private String userId; // 회원아이디
    @Column(length = 65, nullable = false)
    private String userPw; //비밀번호
    @Column(length = 45,nullable = false)
    private String userNm; //회원명
    @Column(length = 100)
    private String email; //이메일
    @Column(length = 11)
    private String mobile; // 휴대전화




    @Enumerated(EnumType.STRING)
    @Column(length = 20,nullable = false)
    private UserType type = UserType.USER;



}
