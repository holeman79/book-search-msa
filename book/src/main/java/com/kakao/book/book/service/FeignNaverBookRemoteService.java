package com.kakao.book.book.service;

import com.kakao.book.book.domain.kakao.KakaoBooks;
import com.kakao.book.book.domain.naver.NaverBooks;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverbook", url = "${app.api.naver.domain}", fallbackFactory = FeignNaverBookRemoteServiceFallbackFactory.class)
public interface FeignNaverBookRemoteService {
    @GetMapping(value = "${app.api.naver.path}", headers = {"X-Naver-Client-Id=${app.api.naver.clientId}", "X-Naver-Client-Secret=${app.api.naver.clientSecret}"})
    NaverBooks getNaverBooks(@RequestParam("query") String query);

//    @GetMapping("/api/products/options/{optionId}")
//    String getProductOption(@PathVariable("optionId") String optionId);
}
