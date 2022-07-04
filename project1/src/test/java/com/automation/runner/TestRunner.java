package com.automation.runner;

import com.automation.poms.Login;
import com.automation.poms.Manager;
import com.automation.poms.Tester;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = "com/automation/steps")
//(features = "classpath:f1.feature, classpath:f2.feature") for multiple features
public class TestRunner {

    public static WebDriver driver;
    public static Login login;
    public static Manager manager;
    public static Tester tester;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        login = new Login(driver);
        manager = new Manager(driver);
        tester = new Tester(driver);
        wait = new WebDriverWait(driver, 2);
    }
    
    @AfterClass
    public static void teardown(){
        driver.quit();
    }

}
