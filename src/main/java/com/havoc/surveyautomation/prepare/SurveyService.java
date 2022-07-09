package com.havoc.surveyautomation.prepare;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SurveyService {
    private static WebDriver driver;
    private static WebDriverWait wait;
    int slowWaitTime = 5;
    int verySlowWaitTime = 10;
    int fastWaitTime = 2;
    @Autowired
    private NameService nameService;

    @Value("${survey.firstname.path}")
    private String firstNamePath;

    @Value("${survey.lastname.path}")
    private String lastNamePath;

    @Value("${survey.url.root}")
    private String rootUrl;

    @Value("${survey.url.main}")
    private String mainURL;

    public List<String> firstNames = new ArrayList<>();
    public List<String> lastNames = new ArrayList<>();

    public void startSurvey(String webDriverPath){
        nameService.readNameValues(firstNamePath, firstNames);
        nameService.readNameValues(lastNamePath, lastNames);

        // Visit page
        System.setProperty("webdriver.gecko.driver", webDriverPath);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(slowWaitTime, TimeUnit.SECONDS);
        //wait = new WebDriverWait(driver, Duration.ofMillis(10000L));
        driver.get(rootUrl);
        driver.manage().timeouts().implicitlyWait(verySlowWaitTime, TimeUnit.SECONDS);
        driver.get(mainURL);
        driver.manage().timeouts().implicitlyWait(verySlowWaitTime, TimeUnit.SECONDS);

        int counter = 0;

        while(true){
            driver.manage().timeouts().implicitlyWait(slowWaitTime, TimeUnit.SECONDS);
            String name = nameService.buildRandomName(firstNames, lastNames);
            System.out.println(name);
            automateSurvey(name);
            counter++;
            System.out.println("Filled " + counter + " surveys till now.");
        }
    }

    public void automateSurvey(String name){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000L));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Identifiers.NEXT_BUTTON_ID)));

        // Step 1
        wait = new WebDriverWait(driver, Duration.ofMillis(10000L));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Identifiers.NAME_FIELD_ID)));
        WebElement nameTextArea = driver.findElement(By.id(Identifiers.NAME_FIELD_ID));
        nameTextArea.sendKeys(name);
        //WebElement nextButton = driver.findElement(By.id(Identifiers.NEXT_BUTTON_ID));
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(Identifiers.NEXT_BUTTON_ID)));

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        ((JavascriptExecutor) executor).executeScript("arguments[0].click();", nextButton);

        //nextButton.click();
        System.out.println("Name entered");

        Random random = new Random();

        // Step 2
        List<WebElement> step2options = driver.findElements(By.name(Identifiers.STEP_2_WEIGHT)) ;
        int step2Index = random.nextInt(step2options.size());
        //step2options.get(step2Index).click();
        //nextButton.click();
        selectRandomOption(executor, step2options, step2Index);
        verifyAndClickNextButton(wait, executor, nextButton);
        System.out.println("Step 2 Completed");

        // Step 3
        List<WebElement> step3options = driver.findElements(By.name(Identifiers.STEP_3_THIRST)) ;
        int step3Index = random.nextInt(step3options.size());
        //step3options.get(step3Index).click();
        //nextButton.click();
        selectRandomOption(executor, step3options, step3Index);
        verifyAndClickNextButton(wait, executor, nextButton);
        System.out.println("Step 3 Completed");

        // Step 4
        List<WebElement> step4options = driver.findElements(By.name(Identifiers.STEP_4_URINATION)) ;
        int step4Index = random.nextInt(step4options.size());
        //step4options.get(step4Index).click();
        //nextButton.click();
        selectRandomOption(executor, step4options, step4Index);
        verifyAndClickNextButton(wait, executor, nextButton);
        System.out.println("Step 4 Completed");

        // Step 5
        List<WebElement> step5options = driver.findElements(By.name(Identifiers.STEP_5_CUTSWOUND)) ;
        int step5Index = random.nextInt(step5options.size());
        //step5options.get(step5Index).click();
        //nextButton.click();
        selectRandomOption(executor, step5options, step5Index);
        verifyAndClickNextButton(wait, executor, nextButton);
        System.out.println("Step 5 Completed");

        // Step 6
        List<WebElement> step6options = driver.findElements(By.name(Identifiers.STEP_6_VISION)) ;
        int step6Index = random.nextInt(step6options.size());
        //step6options.get(step6Index).click();
        //nextButton.click();
        selectRandomOption(executor, step6options, step6Index);
        verifyAndClickNextButton(wait, executor, nextButton);
        System.out.println("Step 6 Completed");

        // Step 7
        List<WebElement> step7options = driver.findElements(By.name(Identifiers.STEP_7_TINGLE)) ;
        int step7Index = random.nextInt(step7options.size());
        //step7options.get(step7Index).click();
        //nextButton.click();
        selectRandomOption(executor, step7options, step7Index);
        verifyAndClickNextButton(wait, executor, nextButton);
        System.out.println("Step 7 Completed");

        // Step 8
        List<WebElement> step8options = driver.findElements(By.name(Identifiers.STEP_8_TIRED)) ;
        int step8Index = random.nextInt(step8options.size());
        //step8options.get(step8Index).click();
        //nextButton.click();
        selectRandomOption(executor, step8options, step8Index);
        verifyAndClickNextButton(wait, executor, nextButton);
        System.out.println("Step 8 Completed");

        // Step 9
        List<WebElement> step9options = driver.findElements(By.name(Identifiers.STEP_9_DIZZY)) ;
        int step9Index = random.nextInt(step9options.size());
        //step9options.get(step9Index).click();
        selectRandomOption(executor, step9options, step9Index);
        driver.manage().timeouts().implicitlyWait(slowWaitTime, TimeUnit.SECONDS);
        verifyAndAgreeTerms(wait, (JavascriptExecutor) executor);
        //agreeTerms.click();
        //nextButton.click();
        verifyAndClickNextButton(wait, executor, nextButton);
        System.out.println("Step 9 Completed");

        //Report
        driver.manage().timeouts().implicitlyWait(slowWaitTime, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='report-details']//h2")));
        WebElement reportElement = driver.findElement(By.xpath("//div[@id='report-details']//h2"));
        System.out.println(reportElement.getText());

        System.out.println("Starting again");

        driver.get(mainURL);
    }

    public void selectRandomOption(JavascriptExecutor executor, List<WebElement> options, int index){
        ((JavascriptExecutor) executor).executeScript("arguments[0].click();", options.get(index));
    }

    public void verifyAndClickNextButton(WebDriverWait wait, JavascriptExecutor executor, WebElement nextButton){
        wait.until(ExpectedConditions.elementToBeClickable(By.id(Identifiers.NEXT_BUTTON_ID)));
        ((JavascriptExecutor) executor).executeScript("arguments[0].click();", nextButton);
        driver.manage().timeouts().implicitlyWait(fastWaitTime, TimeUnit.SECONDS);
    }

    private void verifyAndAgreeTerms(WebDriverWait wait, JavascriptExecutor executor) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(Identifiers.AGREE_TERMS_CLASS)));
        //WebElement agreeTerms = driver.findElement(By.className("custom-control-label"));
        WebElement agreeTerms = wait.until(ExpectedConditions.elementToBeClickable(By.className(Identifiers.AGREE_TERMS_CLASS)));
        executor.executeScript("arguments[0].click();", agreeTerms);
    }

}
