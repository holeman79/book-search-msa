package com.kakao.book.history.service.impl;

import com.kakao.book.history.domain.BookSearchHistory;
import com.kakao.book.history.repository.BookSearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl {
    private final BookSearchHistoryRepository bookSearchHistoryRepository;

    public void saveBookSearchHistory(String keyword, Long userId){
        bookSearchHistoryRepository.save(BookSearchHistory.builder().keyword(keyword).userId(userId).build());
    }
}
