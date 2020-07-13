package com.kakao.book.book.domain.naver;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class NaverBooks {
    private Date lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;
}
