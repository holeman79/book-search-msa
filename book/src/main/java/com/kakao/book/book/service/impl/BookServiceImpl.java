package com.kakao.book.book.service.impl;

import com.kakao.book.book.config.api.KakaoProperty;
import com.kakao.book.book.config.api.NaverProperty;
import com.kakao.book.book.domain.dto.BookSearchRequest;
import com.kakao.book.book.domain.dto.BookSearchResponse;
import com.kakao.book.book.domain.kakao.KakaoBooks;
import com.kakao.book.book.domain.naver.NaverBooks;
import com.kakao.book.book.service.BookService;
import com.kakao.book.book.service.remote.KakaoBookRemoteService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
