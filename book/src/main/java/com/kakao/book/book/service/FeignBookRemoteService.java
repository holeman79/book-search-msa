package com.kakao.book.book.service;

import com.kakao.book.book.domain.Book;
import com.kakao.book.book.domain.kakao.KakaoBooks;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Null;

@FeignClient(name = "kakao-book", url = "${app.api.kakao.domain}", fallbackFactory = FeignBookRemoteServiceFallbackFactory.class)
public interface FeignBookRemoteService {
    @GetMapping(value = "${app.api.kakao.path}", headers = "Authorization=${app.api.kakao.appKey}")
    Book getKakaoBooks(@RequestParam("query") String query);

//    @GetMapping("/api/products/options/{optionId}")
//    String getProductOption(@PathVariable("optionId") String optionId);
}
