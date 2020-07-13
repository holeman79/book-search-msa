package com.kakao.book.book.domain.naver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class Item {
    private String title;
    private String link;
    private String image;
    private String author;
    private int price;
    private int discount;
    private String publisher;
    private String isbn;       // Integer일 경우 오류 발생
    private String description;
    private Date pubdate;

    public String getPubdateString() {
        if (StringUtils.isEmpty(pubdate)) {
            return "";
        }

        return new SimpleDateFormat("yyyy-MM-dd").format(pubdate);
    }
}
