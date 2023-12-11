package com.example.crawlingpractice.global.crawling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScheduleDTO {

    private String date;
    private String time;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
}
