package com.kakao.book.book.config.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("app.api.naver")
public class NaverProperty {
    private String domain;
    private String path;
    private String clientId;
    private String clientSecret;
}
