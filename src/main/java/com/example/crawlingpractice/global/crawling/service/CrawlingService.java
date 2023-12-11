package com.example.crawlingpractice.global.crawling.service;

import com.example.crawlingpractice.global.crawling.WebDriverUtil;
import com.example.crawlingpractice.global.crawling.dto.ScheduleDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService {

    public List<ScheduleDTO> scrapeSchedule() {
        List<ScheduleDTO> schedules = new ArrayList<>();

        WebDriver webDriver = WebDriverUtil.getChromeDriver();
        try {
            webDriver.get("https://www.koreabaseball.com/Schedule/Schedule.aspx");
            Select monthSelector = new Select(webDriver.findElement(By.id("ddlMonth")));
            monthSelector.selectByIndex(3);
            Document doc = Jsoup.parse(webDriver.getPageSource());
            Elements baseballSchedules = doc.select("#tblScheduleList > tbody > tr");

            String currentDay = null;
            for (Element schedule : baseballSchedules) {
                Element day = schedule.selectFirst("td.day");
                Element time = schedule.selectFirst("td.time");
                Element awayTeam = schedule.selectFirst("td.play > span");
                //Element awayScore = schedule.selectFirst("td.play > em > span");
                Element homeTeam = schedule.selectFirst("td.play > span:nth-child(3)");
                //Element homeScore = schedule.selectFirst("td.play > em > span:nth-child(1)");

                if (day != null) {
                    if (currentDay == null || !currentDay.equals(day.text())) {
                        currentDay = day.text();
                    }
                }

                if (time != null) {
//                    ScheduleDTO scheduleDTO = new ScheduleDTO(currentDay, time.text(),
//                            homeTeam.text(),
//                            awayTeam.text(), Integer.parseInt(homeScore.text()),
//                            Integer.parseInt(awayScore.text()));
                    ScheduleDTO scheduleDTO = new ScheduleDTO(currentDay, time.text(),
                            homeTeam.text(), awayTeam.text(), 0, 0);
                    schedules.add(scheduleDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }

        return schedules;
    }
}
