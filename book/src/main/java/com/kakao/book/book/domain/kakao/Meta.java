package com.kakao.book.book.domain.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Meta {
    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;
}