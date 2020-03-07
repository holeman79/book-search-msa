package com.kakao.book.book.domain.dto;

import com.kakao.book.book.domain.Book;
import com.kakao.book.book.domain.kakao.Document;
import com.kakao.book.book.domain.kakao.KakaoBooks;
import com.kakao.book.book.domain.kakao.Meta;
import com.kakao.book.book.domain.naver.NaverBooks;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

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
                kakaoBooks -> {
                    BookSearchResponse response = new BookSearchResponse();

                    Meta meta = kakaoBooks.getMeta();
                    response.setTotalBooks(meta.getPageableCount());
                    response.setTotalPage((int)Math.ceil((double)meta.getPageableCount() / bookSearchRequest.getSize()));
                    response.setCurrentPage(bookSearchRequest.getPage());
                    response.setFirst(bookSearchRequest.getPage() == 1);
                    response.setLast(meta.getIsEnd());
                    response.setApiName("KAKAO");

                    response.books = kakaoBooks.getDocuments()
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
        );
        return bookSearchResponse;
    }


    public static Mono<BookSearchResponse> naverBookResponseMapper(Mono<NaverBooks> naverBooksMono, BookSearchRequest bookSearchRequest){
        Mono<BookSearchResponse> bookSearchResponse = naverBooksMono.map(
                naverBooks -> {
                    BookSearchResponse response = new BookSearchResponse();
                    response.setTotalBooks(naverBooks.getTotal());
                    int totalPage = (int)Math.ceil((double)naverBooks.getTotal() / bookSearchRequest.getSize());
                    response.setTotalPage(totalPage);
                    response.setCurrentPage(bookSearchRequest.getPage());
                    response.setFirst(bookSearchRequest.getPage() == 1);
                    response.setLast(bookSearchRequest.getPage() == totalPage);
                    response.setApiName("NAVER");

                    response.books = naverBooks.getItems()
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
        );
        return bookSearchResponse;
    }

}
