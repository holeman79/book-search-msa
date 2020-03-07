package com.kakao.book.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Getter
@Setter
public class BookSearchRequest {
    @NotNull
    private String query;

    private String sort = "accuracy";

    @Min(1)
    @Max(100)
    private Integer page = 1;

    @Min(1)
    @Max(50)
    private Integer size = 10;

    private String target = "title";

    @Getter
    @AllArgsConstructor
    public enum SortType{
        sim("accuracy"),
        date("latest");

        private String sort;

        public static SortType findByString(String sort){
            return Arrays.stream(SortType.values())
                    .filter(s -> s.getSort().equals(sort))
                    .findFirst()
                    .orElseGet(() -> sim);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum TargetType{
        d_titl("title"),       // 제목 검색
        d_isbn("isbn"),        // ISBN 검색
        d_publ("publisher"),   // 출판사 검색
        d_auth("person");      // 인명 검색

        private String target;

        public static TargetType findByString(String target) {
            return Arrays.stream(TargetType.values())
                    .filter(t -> t.target.equals(target))
                    .findFirst()
                    .orElseGet(() -> d_titl);
        }
    }

    public String getKakaoUri(String uri) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        builder.queryParam("query", this.query);

        if (StringUtils.hasText(this.sort)) {
            builder.queryParam("sort", this.sort);
        }
        if (this.page != null) {
            builder.queryParam("page", this.page);
        }
        if (this.size != null) {
            builder.queryParam("size", this.size);
        }
        if (StringUtils.hasText(this.target)) {
            builder.queryParam("target", this.target);
        }

        return builder.build().toString();
    }

    public String getNaverUri(String uri) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        if (!StringUtils.hasText(this.target)) {
            builder.queryParam("query", this.query);
        } else {
            builder.queryParam(TargetType.findByString(this.target).toString(), this.query);
        }

        if (StringUtils.hasText(this.sort)) {
            builder.queryParam("sort", SortType.findByString(this.sort).toString());
        }
        if (this.page != null) {
            builder.queryParam("start", this.page);
        }
        if (this.size != null) {
            builder.queryParam("display", this.size);
        }

        return builder.build().toString();
    }
}
