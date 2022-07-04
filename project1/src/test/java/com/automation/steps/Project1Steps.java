package com.automation.steps;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.runner.TestRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Project1Steps {
    
    /*
     * User agnostic steps
     */

    @Given("the employee is on the login page")
    public void the_employee_is_on_the_login_page() {  
        TestRunner.driver.get("File://C:/Users/nixen/OneDrive/Desktop/Projects/Project1/Automation-Project/Foundations-Project/project1/src/test/resources/webpages/login.html");
    }

    @When("the employee clicks the login button")
    public void the_employee_clicks_the_login_button() {   
        TestRunner.login.clickButton();   
    }
    
    /*
     * Manager steps
     */
    
    @When("the manager enters his correct username")
    public void the_manager_enters_his_correct_username() {
        TestRunner.login.enterUsername("manager");
    }

    @When("the manager enters his correct password")
    public void the_manager_enters_his_correct_password() {
        TestRunner.login.enterPassword("password");
    }
 
    @Then("the manager should be logged in to the manager homepage")
    public void the_manager_should_be_logged_in_to_the_manager_homepage() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Manager Page"));
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals("Manager Page", title);
    }


    @Given("the manager is logged in to the manager homepage")
    public void the_manager_is_logged_in_to_the_manager_homepage(){
        TestRunner.driver.get("File://C:/Users/nixen/OneDrive/Desktop/Projects/Project1/Automation-Project/Foundations-Project/project1/src/test/resources/webpages/login.html");
        TestRunner.login.enterUsername("manager");
        TestRunner.login.enterPassword("password");
        TestRunner.login.clickButton();  
    }

    @When("the manager enters the defect name")
    public void the_manager_enters_the_defect_name(){
        TestRunner.wait.until(ExpectedConditions.titleIs("Manager Page"));
        TestRunner.manager.enterDefectName("automated test");
    }

    @When("the manager selects a tester")
    public void the_manager_selects_a_tester(){
        TestRunner.manager.selectTester();
    }

    @When("the manager clicks the assign button")
    public void the_manager_clicks_the_assign_button(){
        TestRunner.manager.clickAssignButton();
    }

    @Then("the new defect is created and assigned")
    public void the_new_defect_is_created(){
        boolean alertExists;
        if (TestRunner.wait.until(ExpectedConditions.alertIsPresent()) == null){
            alertExists = false;
        } else {
            alertExists = true;
            Alert alert = TestRunner.driver.switchTo().alert();
            alert.accept();
        }

        Assert.assertEquals(true, alertExists); 
    }


    /*
     * Tester steps
     */

    @When("the tester enters his correct username")
    public void the_tester_enters_his_correct_username() {
        TestRunner.login.enterUsername("tester");
    }
    @When("the tester enters his correct password")
    public void the_tester_enters_his_correct_password() {
        TestRunner.login.enterPassword("password");
    }

    @Then("the tester should be logged in to the tester homepage")
    public void the_tester_should_be_logged_in_to_the_tester_homepage() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Tester Page"));
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals("Tester Page", title);
    }


    @Given("the tester is logged in to the tester homepage")
    public void the_tester_is_logged_in_to_the_tester_homepage() {
        //reset defect to pending
        TestRunner.driver.get("File://C:/Users/nixen/OneDrive/Desktop/Projects/Project1/Automation-Project/Foundations-Project/project1/src/test/resources/webpages/login.html");
        TestRunner.login.enterUsername("manager");
        TestRunner.login.enterPassword("password");
        TestRunner.login.clickButton();  
        TestRunner.wait.until(ExpectedConditions.titleIs("Manager Page"));
        TestRunner.manager.selectDefectStatus("Pending");
        TestRunner.manager.clickUpdateDefectButton();

        TestRunner.driver.get("File://C:/Users/nixen/OneDrive/Desktop/Projects/Project1/Automation-Project/Foundations-Project/project1/src/test/resources/webpages/login.html");
        TestRunner.login.enterUsername("tester");
        TestRunner.login.enterPassword("password");
        TestRunner.login.clickButton();
        TestRunner.wait.until(ExpectedConditions.titleIs("Tester Page"));
    }

	@Then("the tester should be able to view defects assigned to them")
    public void the_tester_should_be_able_to_view_defects_assigned_to_them() {  
        Boolean isPresent = TestRunner.driver.findElements(By.id("selector52984")).size() > 0;
        Assert.assertEquals(true, isPresent);   
    }

    @When ("the tester selects the accept option")
    public void the_tester_selects_the_accept_option(){
        TestRunner.tester.selectDefectStatus("Accepted");
    }

    @When ("the tester clicks the update button")
    public void the_tester_clicks_the_update_button(){
        TestRunner.tester.clickUpdateDefectButton();
    }

    @Then("the tester should accept the defect")
    public void the_tester_should_accept_the_defect(){
        WebElement elem = TestRunner.driver.findElement(By.id("statusTD52984"));
        TestRunner.wait.until(ExpectedConditions.textToBePresentInElement(elem, "Accepted"));
        String status = elem.getAttribute("innerHTML");
        Assert.assertEquals("Accepted", status); 
    }


    
}
