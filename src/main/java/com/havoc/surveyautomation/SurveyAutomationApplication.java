package com.havoc.surveyautomation;

import com.havoc.surveyautomation.prepare.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SurveyAutomationApplication implements CommandLineRunner {

    @Autowired
    private SurveyService surveyService;

    @Value("${webdriver.chrome.driver}")
    private String webDriverPath;

    public static void main(String[] args) {
        SpringApplication.run(SurveyAutomationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        surveyService.startSurvey(webDriverPath);
    }

}
