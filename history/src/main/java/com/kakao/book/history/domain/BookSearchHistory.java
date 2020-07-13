package com.kakao.book.history.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOOK_SEARCH_HISTORIES")
@Getter
public class BookSearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_SEARCH_HISTORY_ID")
    private Long id;

    @Column(nullable = false)
    private String keyword;

    private Long userId;

    @CreatedDate
    private LocalDateTime createdDate;
}
