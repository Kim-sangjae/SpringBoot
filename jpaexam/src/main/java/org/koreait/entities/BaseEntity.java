package org.koreait.entities;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract public class BaseEntity { //

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime regDt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedBy
    private LocalDateTime modDt;




}
