package com.kakao.book.book.domain.dto;

import com.kakao.book.book.domain.Book;
import com.kakao.book.book.domain.kakao.Document;
import com.kakao.book.book.domain.kakao.KakaoBooks;
import com.kakao.book.book.domain.kakao.Meta;
import com.kakao.book.book.domain.naver.Item;
import com.kakao.book.book.domain.naver.NaverBooks;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
public class BookSearchResponse {
    private Integer totalBooks; // pageableCount
    private Integer totalPage; // = pageableCount / size
    private Integer currentPage;
    private Boolean first;
    private Boolean last;
    private String apiName;

    private List<Book> books;

    public static Mono<BookSearchResponse> kakaoBookResponseMapper(Mono<KakaoBooks> kakaoBooksMono, BookSearchRequest bookSearchRequest){
        Mono<BookSearchResponse> bookSearchResponse = kakaoBooksMono.map(
                kakaoBooks -> createBookSearchResponse(kakaoBooks.getMeta(), kakaoBooks.getDocuments(), bookSearchRequest));
        return bookSearchResponse;
    }

    public static Mono<BookSearchResponse> naverBookResponseMapper(Mono<NaverBooks> naverBooksMono, BookSearchRequest bookSearchRequest){
        Mono<BookSearchResponse> bookSearchResponse = naverBooksMono.map(
                naverBooks -> createBookSearchResponse(naverBooks.getLastBuildDate(), naverBooks.getTotal(), naverBooks.getStart(),
                        naverBooks.getDisplay(), naverBooks.getItems(), bookSearchRequest));
        return bookSearchResponse;
    }

    private static BookSearchResponse createBookSearchResponse(Meta meta, List<Document> documents, BookSearchRequest bookSearchRequest){
        BookSearchResponse response = new BookSearchResponse();
        response.totalBooks = meta.getPageableCount();
        response.totalPage = (int)Math.ceil((double)meta.getPageableCount() / bookSearchRequest.getSize());
        response.currentPage = bookSearchRequest.getPage();
        response.first = bookSearchRequest.getPage() == 1;
        response.last = meta.getIsEnd();
        response.apiName = "KAKAO";
        response.books = documents
                .stream()
                .map(document ->
                        Book.builder()
                                .title(document.getTitle())
                                .thumbnail(document.getThumbnail())
                                .contents(document.getContents())
                                .isbn(document.getIsbn())
                                .publisher(document.getPublisher())
                                .publishDate(document.getDatetimeFormat())
                                .price(document.getPrice())
                                .salePrice(document.getSalePrice())
                                .authors(document.getAuthorString())
                                .build()
                ).collect(Collectors.toList());
        return response;
    }

    private static BookSearchResponse createBookSearchResponse(Date lastBuildDate, int total, int start, int display, List<Item> items, BookSearchRequest bookSearchRequest){
        BookSearchResponse response = new BookSearchResponse();
        response.totalBooks = total;
        int totalPage = (int)Math.ceil((double)total / bookSearchRequest.getSize());
        response.totalPage = totalPage;
        response.currentPage = bookSearchRequest.getPage();
        response.first = bookSearchRequest.getPage() == 1;
        response.last = bookSearchRequest.getPage() == totalPage;
        response.apiName = "NAVER";
        response.books = items
                .stream()
                .map(item ->
                        Book.builder()
                                .title(item.getTitle())
                                .thumbnail(item.getImage())
                                .contents(item.getDescription())
                                .isbn(item.getIsbn())
                                .publisher(item.getPublisher())
                                .publishDate(item.getPubdateString())
                                .price(item.getPrice())
                                .salePrice(item.getDiscount())
                                .authors(item.getAuthor())
                                .build()
                ).collect(Collectors.toList());
        return response;
    }

}
