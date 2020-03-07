package com.kakao.book.book.domain.kakao;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class KakaoBooks {
    private Meta meta;
    private List<Document> documents;
}
