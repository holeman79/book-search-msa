package com.kakao.book.history.repository;

import com.kakao.book.history.domain.BookSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookSearchHistoryRepository extends JpaRepository<BookSearchHistory, Long> {
}
