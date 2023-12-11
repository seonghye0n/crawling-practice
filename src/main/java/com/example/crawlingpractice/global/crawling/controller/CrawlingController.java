package com.example.crawlingpractice.global.crawling.controller;

import com.example.crawlingpractice.global.crawling.dto.ScheduleDTO;
import com.example.crawlingpractice.global.crawling.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scrape")
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping("/schedules")
    public ResponseEntity<?> getSchedules() {
        List<ScheduleDTO> schedules = crawlingService.scrapeSchedule();

        return ResponseEntity.ok(schedules);
    }
}
