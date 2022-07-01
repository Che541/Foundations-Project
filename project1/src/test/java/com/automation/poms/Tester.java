package com.automation.poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class Tester {
    
    private WebDriver driver;

    @FindBy(id = "update52984")
    public WebElement updateDefectButton;

    /* 
    @FindBy(id = "projectTitle")
    public WebElement titleInput;
    @FindBy(id = "projectAbout")
    public WebElement aboutInput;
    @FindBy(id = "createProjectButton")
    public WebElement createProjectButton;

    @FindBy(id = "summaryReason")
    public WebElement summaryReasonInput;
    @FindBy(id = "createSummaryButton")
    public WebElement createSummaryButton;

    @FindBy(id = "caseFeature")
    public WebElement caseFeatureInput;
    @FindBy(id = "createCaseButton")
    public WebElement createCaseButton;
    */

    //constructor
    public Tester(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Update defect methods
    public void selectDefectStatus(String status){
        Select drpStatus = new Select(this.driver.findElement(By.id("selector52984")));
        drpStatus.selectByValue(status);
    }

    public void clickUpdateDefectButton(){
        this.updateDefectButton.click();
    }

    /*******************
    //Create project methods
    public void enterProjectTitle(String title){
        this.titleInput.sendKeys(title);
    }

    public void enterAboutProject(String about){
        this.aboutInput.sendKeys(about);
    }

    public void clickCreateProjectButton(){
        this.createProjectButton.click();
    }

    //Create summary methods
    public void selectProject(){
        Select drpProjects = new Select(driver.findElement(By.id("projectSelector")));
        drpProjects.selectByValue("94375");
    }

    public void enterSummaryReason(String reason){
        this.summaryReasonInput.sendKeys(reason);
    }

    public void clickCreateSummaryButton(){
        this.createSummaryButton.click();
    }

    //Create Case methods
    public void selectSummary(){
        Select drpSummaries = new Select(driver.findElement(By.id("summarySelector")));
        drpSummaries.selectByValue("53101");
    }
    public void enterCaseFeature(String feature){
        this.caseFeatureInput.sendKeys(feature);
    }
    public void selectResult(){
        Select drpResults = new Select(driver.findElement(By.id("caseResultSelector")));
        drpResults.selectByValue("Pass");
    }
    public void clickCreateCaseButton(){
        this.createCaseButton.click();
    }

    //Delete Case Method
    public void clickDeleteCaseButton(String id){
        String deleteId = "delete" + id;
        driver.findElement(By.id(deleteId)).click();
    }
    *********************/


}
