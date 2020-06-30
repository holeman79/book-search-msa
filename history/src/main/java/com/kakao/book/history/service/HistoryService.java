package com.kakao.book.history.service;

public interface HistoryService {
    void saveBookSearchHistory(String keyword, Long userId);
}
