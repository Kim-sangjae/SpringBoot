package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter@Setter
@MappedSuperclass // 이 애노테이션을 클래스에 정의해야 상속받은 공통 추상클래스의 속성들을 사용할수 있다
@EntityListeners(AuditingEntityListener.class) // 엔티티의 변화를 감지한다
abstract public class BaseEntity { // 공통적인 부분은 객체를 만들지 않을것이다 때문에 추상클래스로 생성


    @CreatedDate // 엔티티에 추가될때
    @Column(updatable = false) // 처음추가하는 시간을 변경하지 못하게 된다
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regDt;

    @LastModifiedDate // 엔티티의 상태에따라 추가될때
    @Column(insertable = false) // 데이터가 변경될때만  값변경 할 수 있게함
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modDt;




}
