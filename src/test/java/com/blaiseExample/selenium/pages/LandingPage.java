package com.blaiseExample.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.qameta.allure.Allure;

public class LandingPage extends BasePage {
    //POM
    private final String path_successToast = "div[role='status']"; 

    public LandingPage(WebDriver webDriver){
        super(webDriver);
    }

    @Override
    public void validatePageStructure(){
        // TODO 
    }

    public void verifyLoginSuccessToast(String expectedText){
        Allure.step( "Verifying Login Success Toast displays", ()-> {
            // verify the toast displays within 10 seconds of pageload
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(this.path_successToast)));
           
            // verify the message matches the expected one 
            String message = toast.getText().trim(); 
            Assert.assertTrue(
                message.contains(expectedText),
                "expected text : " + expectedText + ", but found : "+ message 
            );
            takeScreenshot(); 
        });
    }  
}