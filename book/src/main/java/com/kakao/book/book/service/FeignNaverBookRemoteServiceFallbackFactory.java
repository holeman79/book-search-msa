package com.kakao.book.book.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignNaverBookRemoteServiceFallbackFactory implements FallbackFactory<FeignNaverBookRemoteService> {
    @Override
    public FeignNaverBookRemoteService create(Throwable cause) {
        System.out.println("naver t : " + cause);

        return null;
    }
}
