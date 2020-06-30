package com.kakao.book.history.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOOK_SEARCH_HISTORIES")
@Getter
@Setter
@Builder
public class BookSearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_SEARCH_HISTORY_ID")
    private Long id;

    @Column(name = "KEYWORD")
    private String keyword;

    @Column(name = "USER_ID")
    private Long userId;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;
}
