package com.kakao.book.book.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignBookRemoteServiceFallbackFactory implements FallbackFactory<FeignBookRemoteService> {
    @Override
    public FeignBookRemoteService create(Throwable cause) {
        System.out.println("t : " + cause);
        return null;
    }
}
