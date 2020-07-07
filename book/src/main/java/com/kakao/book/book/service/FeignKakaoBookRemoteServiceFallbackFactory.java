package com.kakao.book.book.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignKakaoBookRemoteServiceFallbackFactory implements FallbackFactory<FeignKakaoBookRemoteService> {
    @Override
    public FeignKakaoBookRemoteService create(Throwable cause) {
        System.out.println("kakao t : " + cause);
        return null;
    }
}
