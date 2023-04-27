package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardData extends BaseUserEntity{

    @Id
    @GeneratedValue
    private Long id;  // 게시글 번호

    @Column(nullable = false)
    private String subject;  // 제목
    @Lob
    @Column(nullable = false)
    private String content; // 내용

}
