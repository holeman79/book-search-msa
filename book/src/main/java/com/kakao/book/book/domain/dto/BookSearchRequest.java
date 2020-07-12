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
import java.util.Optional;

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

    private String target;

    private Long userId;

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

    public String getKakaoUri(String uri) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        Optional.ofNullable(this.query).orElseThrow(IllegalArgumentException::new);
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

        builder.queryParam("query", this.query);
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
