# 🚀  Selenium Automation Framework Example

This repository was built as a toy project to give a simplified example of my ability to architect and deploy Java-based testing automation both in Browser and Native Mobile Applications. It Utilizes : 

-  **Selenium Web Automation** for browser-based testing
-  **TestNG** for organized test orchestration
-  **Allure Reporting** for customized test reports
-  **WebDriverManager** for automatic driver binaries

### Potential Future Improvements
- **Appium Mobile Automation** for Android app testing
- **Android Studio** for local Device Emulation (This could be scaled to a cloud provider like BrowserStack or AWS Device Farm for enterprise use in pipelines)

---

## Local Demo 

[![Demo Video](https://img.youtube.com/vi/xybALfv08JQ/0.jpg)](https://www.youtube.com/watch?v=xybALfv08JQ)



## 📂 Project Structure

All Selenium test and page model files are found in : 

```
test/java/com/blaiseExample/selenium
```

### Page Model Files

Page model files are where actual locator paths, logic , and page specific helpers are located, they inherit from the base page class, which contains common helpers and abstract methods for validating key architecture. These files should NOT contain Tests.

### Test Files

Test Files contain setup and teardown methods,  as well as functional tests for a respective page or feature. Logic and interaction code should be kept to a minimum in these files , and instead pushed down to the Page Model level. Ideally , Tests should mainly be making function calls to Page Model instances for interaction , instead of doing the interation explicitly. 

--- 

## Sample Tests 

**LoginPageTest** : This Test file is written in Selenium and targets the login page of my site `30DaySDET.dev`, which is a playground site I'm currently developing in NextJS to help aspiring SDET's practice automation ( think leetcode for testing automation). It covers page architecture assertion , positive and negative testing ,and some other fundamentals.

## ⚙️ Prerequisites

- Java 17+
- Maven 3.x

---

## Getting Started 
1) **clone repo and navigate to root directory**
    ```
    git clone https://github.com/BlaiseMoses01/Selenium_Appium_Example.git
    cd Selenium_Appium_Example
    ```
2) **install dependencies**
    - make sure you have Maven and Java installed 
    ```
    java -version
    mvn -version
    ```
    if you need to download either , they can be found online

3) **run install to install remaining dependencies** : Maven will handle all other required dependencies

    ```
    mvn clean install
    ```

    Note : by default this will run all tests after the project is compiled, To forgo this , add the flag ```-DskipTests``` to the end of the command
---

## Running Tests 
You can run all tests with the following command : 

```mvn clean test``` 

Or if you would like to run a particular test file , you can add it as a flag like this : 

```mvn -Dtest=LoginPageTest clean test```

Additionally , I've integrated support for running both headless and normally, if you would like a run to be headless, simply add the flag : ```-Dheadless=true``` to the above command. 


## Allure Reporting

I have integrated Allure Reporting into this project to provide clean , clear test results following runs. To serve the report locally, you can use Allure Serve as follows: 

```allure serve target/allure-results```

this will compile and open the Allure report in your browser for your review. 

NOTE: this assumes you have alllure CLI installed, if you do not , you will need to install it via Scoop : 

```scoop install allure```

or manually by downloading online.

## Github Actions Workflow

In addition to local support, I've also included a Github actions flow for this project to showcase the ability of the framework to be integrated into regression pipelines. Upon a push to the main branch of the remote repo, this script runs the Selenium tests, compiles the allure report, and saves it as an artifact on the workflow. 
    
## Credits

Written by Blaise Moses 

[Email](mailto:bamoses2001@gmail.com)
[Linkedin](https://www.linkedin.com/in/blaise-moses/) 
