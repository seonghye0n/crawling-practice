package com.example.crawlingpractice.global.crawling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class WebDriverUtil {
    private static String WEB_DRIVER_PATH = "src/main/resources/static/driver/chromedriver";

    public static WebDriver getChromeDriver() {
        if (ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        }

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");

        WebDriver driver = new ChromeDriver(chromeOptions);
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }
}
