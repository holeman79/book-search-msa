package com.kakao.book.book.api;

import com.kakao.book.book.domain.dto.BookSearchRequest;
import com.kakao.book.book.domain.dto.BookSearchResponse;
import com.kakao.book.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Mono<BookSearchResponse>> getBooks(@Valid BookSearchRequest bookSearchRequest) {
        Mono<BookSearchResponse> bookSearchResponse = bookService.getBooksByExternalApi(bookSearchRequest);
        return ResponseEntity.ok(bookSearchResponse);
    }
}
