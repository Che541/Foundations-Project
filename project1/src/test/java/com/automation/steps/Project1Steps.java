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

    @Then("the API receives the command")
    public void the_API_receives_the_command(){
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


    @Given("the tester has a pending defect")
    public void the_tester_has_a_pending_defect() {
        TestRunner.login.loginUser("manager", "password");
        TestRunner.manager.selectDefectStatus("Pending");
        TestRunner.manager.clickUpdateDefectButton();
        WebElement elem = TestRunner.driver.findElement(By.id("statusTD52984"));
        TestRunner.wait.until(ExpectedConditions.textToBePresentInElement(elem, "Pending"));
    }

    @Given("the tester has an accepted defect")
    public void the_tester_has_an_accepted_defect() {
        TestRunner.login.loginUser("manager", "password");
        TestRunner.manager.selectDefectStatus("Accepted");
        TestRunner.manager.clickUpdateDefectButton();
        WebElement elem = TestRunner.driver.findElement(By.id("statusTD52984"));
        TestRunner.wait.until(ExpectedConditions.textToBePresentInElement(elem, "Accepted"));
    }

    @Given("the tester is logged in to the tester homepage")
    public void the_tester_is_logged_in_to_the_tester_homepage() {
        TestRunner.login.loginUser("tester", "password");
    }

	@Then("the tester should be able to view defects assigned to them")
    public void the_tester_should_be_able_to_view_defects_assigned_to_them() {  
        Boolean isPresent = TestRunner.driver.findElements(By.id("statusTD52984")).size() > 0;
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

    @When("the tester selects the decline option")
    public void the_tester_selects_the_decline_option() {
        TestRunner.tester.selectDefectStatus("Declined");
    }

    @When("the tester should decline the defect")
    public void the_tester_should_decline_the_defect() {
        WebElement elem = TestRunner.driver.findElement(By.id("statusTD52984"));
        TestRunner.wait.until(ExpectedConditions.textToBePresentInElement(elem, "Declined"));
        String status = elem.getAttribute("innerHTML");
        Assert.assertEquals("Declined", status); 
    }


    @When("the tester selects the fixed option")
    public void the_tester_selects_the_fixed_option() {
        TestRunner.tester.selectDefectStatus("Fixed");
    }

    @Then("the tester should declare the defect as fixed")
    public void the_tester_should_declare_the_defect_as_fixed() {
        WebElement elem = TestRunner.driver.findElement(By.id("statusTD52984"));
        TestRunner.wait.until(ExpectedConditions.textToBePresentInElement(elem, "Fixed"));
        String status = elem.getAttribute("innerHTML");
        Assert.assertEquals("Fixed", status); 
    }

    @When("the tester selects the reject option")
    public void the_tester_selects_the_reject_option() {
        TestRunner.tester.selectDefectStatus("Rejected");
    }

    @Then("the tester should reject the defect")
    public void the_tester_should_reject_the_defect() {
        WebElement elem = TestRunner.driver.findElement(By.id("statusTD52984"));
        TestRunner.wait.until(ExpectedConditions.textToBePresentInElement(elem, "Rejected"));
        String status = elem.getAttribute("innerHTML");
        Assert.assertEquals("Rejected", status); 
    }

    @When("the tester selects the shelved option")
    public void the_tester_selects_the_shelved_option() {
        TestRunner.tester.selectDefectStatus("Shelved");
    }

    @Then("the tester should shelve the defect")
    public void the_tester_should_shelve_the_defect() {
        WebElement elem = TestRunner.driver.findElement(By.id("statusTD52984"));
        TestRunner.wait.until(ExpectedConditions.textToBePresentInElement(elem, "Shelved"));
        String status = elem.getAttribute("innerHTML");
        Assert.assertEquals("Shelved", status); 
    }



    @When("the tester enters a project title")
    public void the_tester_enters_a_project_title() {
        TestRunner.tester.enterProjectTitle("test");
    }

    @When("the tester enters info about the project")
    public void the_tester_enters_info_about_the_project() {
        TestRunner.tester.enterAboutProject("Created by automated testing");
    }

    @When("the tester clicks the create project button")
    public void the_tester_clicks_the_create_project_button() {
        TestRunner.tester.clickCreateProjectButton();
    }

    @When("the tester selects a project to attach the test summary report to")
    public void the_tester_selects_a_project_to_attach_the_test_summary_report_to() {
        TestRunner.tester.selectProject();
    }

    @When("the tester enters a reason for testing")
    public void the_tester_enters_a_reason_for_testing() {
        TestRunner.tester.enterSummaryReason("Automated testing");
    }

    @When("the tester clicks the create summary button")
    public void the_tester_clicks_the_create_summary_button() {
        TestRunner.tester.clickCreateSummaryButton();
    }

    @When("the tester selects a test summary report to attach the test case to")
    public void the_tester_selects_a_test_summary_report_to_attach_the_test_case_to() {
        TestRunner.tester.selectSummary();
    }

    @When("the tester enters the feature tested")
    public void the_tester_enters_the_feature_tested() {
        TestRunner.tester.enterCaseFeature("Automated Testing");
    }

    @When("the tester selects a result")
    public void the_tester_selects_a_result() {
        TestRunner.tester.selectResult();
    }

    @When("the testers clicks the create test case button")
    public void the_testers_clicks_the_create_test_case_button() {
    TestRunner.tester.clickCreateCaseButton();
    }


}
