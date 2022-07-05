package com.automation.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.runner.TestRunner;

public class Login {
    
    private WebDriver driver;

    @FindBy(id = "username")
    public WebElement usernameInput;
    @FindBy(id = "password")
    public WebElement passwordInput;
    @FindBy(id = "submitButton")
    public WebElement loginButton;

    //constructor
    public Login(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterUsername(String username){
        this.usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        this.passwordInput.sendKeys(password);
    }

    public void clickButton(){
        this.loginButton.click();
    }

    public void loginUser(String username, String password){
        TestRunner.driver.get("File://C:/Users/nixen/OneDrive/Desktop/Projects/Project1/Automation-Project/Foundations-Project/project1/src/test/resources/webpages/login.html");
        enterUsername(username);
        enterPassword(password);
        clickButton();
        TestRunner.wait.until(ExpectedConditions.or(ExpectedConditions.titleIs("Tester Page"), ExpectedConditions.titleIs("Manager Page")));
    }

}
