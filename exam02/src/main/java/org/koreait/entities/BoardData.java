package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
@Entity
public class BoardData {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id; // 게시글 번호
    @Column(length = 150, nullable = false)
    private String subject; // 게시글제목
    @Lob
    @Column(nullable = false)
    private String content; // 게시글 내용


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regDt; // 작성일시

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modDt; // 수정일시



    @ManyToOne
    @JoinColumn(name="userNo")
    private Member member;






}
