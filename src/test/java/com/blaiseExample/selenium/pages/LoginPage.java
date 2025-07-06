package com.blaiseExample.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.qameta.allure.Allure;

public class LoginPage extends BasePage{
    
    //POM  
    private final String path_emailField ="input[aria-label = 'Email']";  
    private final String path_passwordField ="input[aria-label ='Password']"; 
    private final String path_loginErrorAlert = "div[role='alert']"; 
    private final String path_forgotPasswordAlert = String.format("//h2[normalize-space(text())=\"%s\"]", "Password Reset");
    private final String path_forgotPasswordButton = String.format("//button[normalize-space(text())=\"%s\"]", "Forgot your password?");
    private final String path_signupButton = String.format("//a[normalize-space(text())=\"%s\"]", "Sign up");
    private final String path_loginButton = String.format("//button[normalize-space(text())=\"%s\"]", "Login");;  
    private final String path_homeButton = String.format("//button[normalize-space(text())=\"%s\"]", "← Back to Home"); ; 

    public LoginPage(WebDriver webDriver){
        super(webDriver);
    }

    @Override
    public void validatePageStructure(){
      Allure.step("Validating presence of key elements for Login Page", ()->{
       Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.30daysdet.dev/login", "unexpected URL");
       Assert.assertTrue((webDriver.findElement(By.cssSelector(this.path_emailField))).isDisplayed(), "Email Field is not present");
       Assert.assertTrue((webDriver.findElement(By.cssSelector(this.path_passwordField))).isDisplayed(), "Password Field is not present"); 
       Assert.assertTrue((webDriver.findElement(By.xpath(this.path_forgotPasswordButton))).isDisplayed(), "Forgot Password button is not present"); 
       Assert.assertTrue((webDriver.findElement(By.xpath(this.path_signupButton))).isDisplayed(), "Signup button is not present"); 
       Assert.assertTrue((webDriver.findElement(By.xpath(this.path_loginButton))).isDisplayed(), "Login button is not present"); 
       Assert.assertTrue((webDriver.findElement(By.xpath(this.path_homeButton))).isDisplayed(), "Home Button is not present"); 
       takeScreenshot(); 
      });
    }

    public void fillLogin(String username,  String password ){
       Allure.step("Filling Login with username : '"+ username + "' and password : '" + password + "'", () -> {
            WebElement userField = webDriver.findElement(By.cssSelector(this.path_emailField));
            WebElement passField = webDriver.findElement(By.cssSelector(this.path_passwordField));
            userField.sendKeys(username);
            passField.sendKeys(password);
            takeScreenshot();
        }); 
    }

    public void verifyLoginErrorMessage(){
        Allure.step("Verifying login error message is  displayed", ()->{
            WebElement loginAlert = webDriver.findElement(By.cssSelector(this.path_loginErrorAlert));
            Assert.assertTrue(loginAlert.isDisplayed());
            String alertText = loginAlert.getText(); 
            Assert.assertTrue(alertText.contains("Invalid email or password.") , 
            "unexpected alert message : " + alertText);
            takeScreenshot(); 
        }); 
    }

    public void verifyPasswordResetMessage(){
        Allure.step("Verifying password reset message is displayed", () ->{
            WebElement passResetHeader = webDriver.findElement(By.xpath(this.path_forgotPasswordAlert));
            Assert.assertTrue(passResetHeader.isDisplayed());
            takeScreenshot();
        });
    }

    public void clickSignupLink(){
        Allure.step("Clicking Signup link", ()-> {
            WebElement signupLink = webDriver.findElement(By.xpath(this.path_signupButton));
            signupLink.click();
            takeScreenshot();
        });
    }
}