package com.kakao.book.book.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BeanConfig {
    @LoadBalanced
    @Bean
    public WebClient webClient(){
        return WebClient.create();
    }
}
