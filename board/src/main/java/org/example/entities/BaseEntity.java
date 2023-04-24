package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass // 다른 엔티티의 공통 클래스
@EntityListeners(AuditingEntityListener.class)
abstract public class BaseEntity {
    @Column(updatable = false) // 수정 불가 , 추가만 가능
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regDt;


    @Column(insertable = false) // 추가불가 , 수정만 가능
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modDt;

}
