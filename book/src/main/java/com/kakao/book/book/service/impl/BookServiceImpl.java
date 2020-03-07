package com.kakao.book.book.service.impl;

import com.kakao.book.book.config.api.KakaoProperty;
import com.kakao.book.book.config.api.NaverProperty;
import com.kakao.book.book.domain.dto.BookSearchRequest;
import com.kakao.book.book.domain.dto.BookSearchResponse;
import com.kakao.book.book.domain.kakao.KakaoBooks;
import com.kakao.book.book.domain.naver.NaverBooks;
import com.kakao.book.book.service.BookService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    private final KakaoProperty kakaoProperty;

    private final NaverProperty naverProperty;

    private final WebClient client;

    @HystrixCommand(commandKey = "booksByKakaoApi", fallbackMethod = "getBooksByNaverApi")
    public Mono<BookSearchResponse> getBooksByKakaoApi(BookSearchRequest bookSearchRequest){
        Mono<KakaoBooks> kakaoBooksMono = client.get()
                .uri(bookSearchRequest.getKakaoUri(kakaoProperty.getDomain() + kakaoProperty.getPath()))
                .header("Authorization", kakaoProperty.getAppKey())
                .retrieve()
                .bodyToMono(KakaoBooks.class)
                .subscribeOn(Schedulers.elastic());
        return BookSearchResponse.kakaoBookResponseMapper(kakaoBooksMono, bookSearchRequest);
    }

    public Mono<BookSearchResponse> getBooksByNaverApi(BookSearchRequest bookSearchRequest, Throwable t){
        log.info(String.format("kakao api call error : %s"), t.getMessage());
        Mono<NaverBooks> naverBooksMono = client.get()
                .uri(bookSearchRequest.getNaverUri(naverProperty.getDomain() + naverProperty.getPath()))
                .header("X-Naver-Client-Id", naverProperty.getClientId())
                .header("X-Naver-Client-Secret", naverProperty.getClientSecret())
                .retrieve()
                .bodyToMono(NaverBooks.class)
                .subscribeOn(Schedulers.elastic());
        return BookSearchResponse.naverBookResponseMapper(naverBooksMono, bookSearchRequest);
    }
}
