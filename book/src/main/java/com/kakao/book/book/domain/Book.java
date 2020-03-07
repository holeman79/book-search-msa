package com.kakao.book.book.domain;

import com.kakao.book.book.domain.kakao.Document;
import com.kakao.book.book.domain.kakao.Meta;
import com.kakao.book.book.domain.naver.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Book {
    private String title;        // 도서 제목
    private String thumbnail;    // 도서 썸네일
    private String contents;     // 도서 소개
    private String isbn;         // 국제 표준 도서번호(ISBN10 ISBN13)
    private String publisher;    // 도서 출판사
    private String publishDate;  // 도서 출판일
    private Integer price;        // 도서 정가
    private Integer salePrice;    // 도서 판매가
    private String authors;      // 도서 저자 리스트

    @Builder
    public Book(String title, String thumbnail, String contents, String isbn, String publisher, String publishDate, Integer price, Integer salePrice, String authors) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.contents = contents;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.price = price;
        this.salePrice = salePrice;
        this.authors = authors;
    }
}
