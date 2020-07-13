package com.kakao.book.book.service;

import com.kakao.book.book.domain.dto.BookSearchRequest;
import com.kakao.book.book.domain.dto.BookSearchResponse;
import com.kakao.book.book.service.remote.KakaoBookRemoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService{
    private final KakaoBookRemoteService kakaoBookRemoteService;

    @Transactional
    public Mono<BookSearchResponse> getBooksByExternalApi(BookSearchRequest bookSearchRequest) {

        return kakaoBookRemoteService.getBooksByKakaoApi(bookSearchRequest);
    }
}
