package com.kakao.book.book.service;

import com.kakao.book.book.domain.Book;
import com.kakao.book.book.domain.dto.BookSearchRequest;
import com.kakao.book.book.domain.dto.BookSearchResponse;
import com.kakao.book.book.domain.kakao.KakaoBooks;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookService {
    Mono<BookSearchResponse> getBooksByExternalApi(BookSearchRequest bookSearchRequest);
}
