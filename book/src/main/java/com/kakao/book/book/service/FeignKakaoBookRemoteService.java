package com.kakao.book.book.service;

import com.kakao.book.book.domain.kakao.KakaoBooks;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaobook", url = "${app.api.kakao.domain}", fallbackFactory = FeignKakaoBookRemoteServiceFallbackFactory.class)
public interface FeignKakaoBookRemoteService {
    @GetMapping(value = "${app.api.kakao.path}", headers = "Authorization=${app.api.kakao.appKey}")
    KakaoBooks getKakaoBooks(@RequestParam("query") String query);

//    @GetMapping("/api/products/options/{optionId}")
//    String getProductOption(@PathVariable("optionId") String optionId);
}
