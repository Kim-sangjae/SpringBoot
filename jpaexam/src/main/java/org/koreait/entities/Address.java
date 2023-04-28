package org.koreait.entities;

import jakarta.persistence.*;

@Entity
public class Address extends BaseEntity{

    @Id
    @GeneratedValue
    private Long Id;

    private String zipcode;
    private String addr1;
    private String addr2;


    @OneToOne(mappedBy = "address")
    private Member member;




}
