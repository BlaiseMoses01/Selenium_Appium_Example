package com.blaiseExample.selenium.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.blaiseExample.selenium.pages.LandingPage;
import com.blaiseExample.selenium.pages.LoginPage;

import io.qameta.allure.testng.AllureTestNg;

@Listeners({AllureTestNg.class})
public class LoginPageTest extends BaseTest{
    private LoginPage loginPage; 

    @BeforeMethod
    public void loadTarget(){
        this.loginPage = new LoginPage(driver);
        loginPage.loadPage("Login","https://www.30daysdet.dev/login"); 
    }
    
    /*
    * Login Page structure assertions :  
    * Validate Login Label , email field,  password field , login button , forgot password link , signup link are present
    */
    
    @Test
    public void assertPageStructure(){
        loginPage.validatePageStructure();
    }
    
    /* Scenario 1 :  Error Banner Displaying for invalid form fill 
    * empty pass n email = error
    * empty pass , filled email = error
    * empty email,  filled pass = error
    * bad email  = error 
    * bad password = error 
    */ 
    
    @DataProvider(name = "invalidLoginConfigs")
    public Object[][] getConfigs(){
        return new Object[][]{
           {"", ""},
           {"test@qa.com",""},
           {"","test123"},
           {"bademail","test123"},
           {"test@qa.com", "badpassword"}
        };
    }

    @Test(dataProvider = "invalidLoginConfigs")
    public void testInvalidLogins(String email , String password){
        loginPage.fillLogin(email, password);
        loginPage.clickButtonByText("Login");
        loginPage.verifyLoginErrorMessage(); 
    }

    // Scenario 2 : Valid login navigates to dashboard and triggers login toast
    @Test 
    public void validLogin(){
         LandingPage landingPage = new LandingPage(driver);
         
         loginPage.fillLogin("test@qa.com", "test123");
         loginPage.clickButtonByText("Login");
         landingPage.verifyRedirect("https://www.30daysdet.dev/dashboard?success=true");
         landingPage.verifyLoginSuccessToast("Login Successful. Welcome!");
    }

    //Scenario 3 : Validate forgot password button triggers email sent notification 
    @Test 
    public void forgotPasswordTrigger(){
        loginPage.clickButtonByText("Forgot your password?");
        loginPage.verifyPasswordResetMessage();
    }

    //Scenario 4 : Validate back to home button navigates to landing page 
    @Test
    public void backToHomeTrigger(){
        loginPage.clickButtonByText("← Back to Home");
        loginPage.verifyRedirect("https://www.30daysdet.dev/");
    }
    //Scenario 5 : Validate signup button navigates to signup page  
    @Test 
    public void signupTrigger() {
        loginPage.clickSignupLink();
        loginPage.verifyRedirect("https://www.30daysdet.dev/signup");
    }

}