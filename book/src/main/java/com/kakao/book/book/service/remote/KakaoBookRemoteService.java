package com.kakao.book.book.service.remote;

import com.kakao.book.book.config.api.KakaoProperty;
import com.kakao.book.book.config.api.NaverProperty;
import com.kakao.book.book.domain.dto.BookSearchRequest;
import com.kakao.book.book.domain.dto.BookSearchResponse;
import com.kakao.book.book.domain.kakao.KakaoBooks;
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
public class KakaoBookRemoteService {
    private final KakaoProperty kakaoProperty;

    private final WebClient client;

    private final NaverBookRemoteService naverBookRemoteService;

    @HystrixCommand(commandKey = "booksByKakaoApi", fallbackMethod = "getBooksByKakaoApiFallback")
    public Mono<BookSearchResponse> getBooksByKakaoApi(BookSearchRequest bookSearchRequest){
        Mono<KakaoBooks> kakaoBooksMono = client.get()
                .uri(bookSearchRequest.getKakaoUri(kakaoProperty.getDomain() + kakaoProperty.getPath()))
                .header("Authorization", kakaoProperty.getAppKey())
                .retrieve()
                .bodyToMono(KakaoBooks.class)
                .subscribeOn(Schedulers.elastic());
        return BookSearchResponse.kakaoBookResponseMapper(kakaoBooksMono, bookSearchRequest);
    }

    public Mono<BookSearchResponse> getBooksByKakaoApiFallback(BookSearchRequest bookSearchRequest, Throwable t){
        log.info(String.format("kakao api call error : %s", t));
        return naverBookRemoteService.getBooksByNaverApi(bookSearchRequest);
    }
}
