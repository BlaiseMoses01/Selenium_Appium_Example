package com.blaiseExample.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    
    // inheritable webdriver browser instance
    protected WebDriver driver;

    // initialize the browser instance before each test 
    @BeforeMethod
    public void setupTests() {
        WebDriverManager.chromedriver().setup();

        // Create ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // Check the "headless" system property
        String headlessProp = System.getProperty("headless", "false");
        boolean isHeadless = headlessProp.equalsIgnoreCase("true");

        if (isHeadless) 
            options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/chrome-user-data-" + System.currentTimeMillis());

        driver = new ChromeDriver(options);
    }

    // close browser instance after each test 
    @AfterMethod
    public void teardownTests() {
        if (driver != null) {
            driver.quit();
        }
    }
}