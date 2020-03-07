package com.kakao.book.book.config.api;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BeanConfig {
    @Bean
    public WebClient webClient(){
        return WebClient.create();
    }
}
