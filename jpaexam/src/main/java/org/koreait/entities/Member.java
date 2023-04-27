package org.koreait.entities;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    private Long userNo;
    private String userId;
    private String userPw;
    private String userNm;

}
