package com.kakao.book.book.service.impl;

import com.kakao.book.book.domain.dto.BookSearchRequest;
import com.kakao.book.book.domain.dto.BookSearchResponse;
import com.kakao.book.book.service.BookService;
import com.kakao.book.book.service.remote.KakaoBookRemoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final KakaoBookRemoteService kakaoBookRemoteService;

    @Override
    public Mono<BookSearchResponse> getBooksByExternalApi(BookSearchRequest bookSearchRequest) {
        return kakaoBookRemoteService.getBooksByKakaoApi(bookSearchRequest);
    }
}
