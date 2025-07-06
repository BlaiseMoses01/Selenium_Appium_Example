package com.blaiseExample.selenium.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    
    //inheritable webdriver browser instance
    protected WebDriver driver;

    // initialize the browser instance before each test 
    @BeforeMethod
    public void setupTests(){
        // using WebDriverManager to automatically get ChromeDriver for machine's current version
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); 
    }

    // close browser instance after each test 
    @AfterMethod
    public void teardownTests(){
        driver.quit(); 
    }
}