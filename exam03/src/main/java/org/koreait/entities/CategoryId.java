package org.koreait.entities;

import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@IdClass(Category.class)
public class CategoryId implements Serializable {
    // 기본키를 묶기위한 클래스
    private String cateCd;
    private String subCateCd;




}
