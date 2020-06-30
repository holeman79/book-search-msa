package com.kakao.book.history.api;

import com.kakao.book.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/histories")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @PostMapping("/books")
    public void saveBookSearchHistory(@RequestParam String keyword, @RequestParam Long userId){
        historyService.saveBookSearchHistory(keyword, userId);
    }
}
