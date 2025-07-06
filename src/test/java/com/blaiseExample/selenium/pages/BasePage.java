package com.blaiseExample.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

public abstract class BasePage{
    WebDriver webDriver; 
    
    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver; 
    }
    
    // abstract method to validate the structure of each page,  REQUIRED 
    public abstract void validatePageStructure(); 

    public void loadPage(String pageName, String path) {
        Allure.step("Navigate to target page: " + pageName, () -> {
            webDriver.get(path);
            takeScreenshot();
        });
    }
    
    // step to click a button by it's text value on the page
    public void clickButtonByText(String label) {
        Allure.step("Click button labeled: " + label, () -> {
            String path = String.format("//button[normalize-space(text())=\"%s\"]", label);
            WebElement targetButton = webDriver.findElement(By.xpath(path));
            targetButton.click();
            takeScreenshot();
        });
    }

    public void verifyRedirect(String expectedUrl){
       Allure.step("Verifying redirect to URL : " + expectedUrl, () -> {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            takeScreenshot();
       });
    }

    // helper method to take screenshot of current application state and add to the Allure report 
    public void takeScreenshot() {
        byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        Allure.getLifecycle().addAttachment("Screenshot", "image/png", "png", screenshot);
    }
}