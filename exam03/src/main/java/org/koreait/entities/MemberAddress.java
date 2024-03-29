package org.koreait.entities;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@Entity
@NoArgsConstructor@AllArgsConstructor
public class MemberAddress extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 10,nullable = false)
    private String zipCode; // 우편번호
    @Column(length = 100,nullable = false)
    private String addr1; // 주소
    @Column(length = 100)
    private String addr2; // 상세주소


    @OneToOne(mappedBy = "address",fetch = FetchType.LAZY)
    Member member;




}
