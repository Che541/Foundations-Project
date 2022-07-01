package com.automation.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;


public class Manager {

    private WebDriver driver;

    @FindBy(id = "defectName")
    public WebElement defectNameInput;
    //@FindBy(id = "selectTester")
    //public WebElement testerSelector;
    @FindBy(id = "assignButton")
    public WebElement assignButton;

    @FindBy(id = "update52984")
    public WebElement updateDefectButton;


    //constructor
    public Manager(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Create defect methods
    public void enterDefectName(String defectName){
        this.defectNameInput.sendKeys(defectName);
    }

    public void selectTester(){
        Select drpTesters = new Select(this.driver.findElement(By.id("selectTester")));
        drpTesters.selectByValue("tester");
    }

    public void clickAssignButton(){
        this.assignButton.click();
    }

    //Update defect methods
     public void selectDefectStatus(String status){
        Select drpStatus = new Select(this.driver.findElement(By.id("selector52984")));
        drpStatus.selectByValue(status);
    }
        
    public void clickUpdateDefectButton(){
        this.updateDefectButton.click();
    }
}