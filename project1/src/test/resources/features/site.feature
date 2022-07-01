Feature: Managers should be able to login to access their homepages.

	Scenario: As a manager I want to sign in so I can view my custom homepage. 
	
			Given the employee is on the login page
			When the manager enters his correct username
			When the manage enters his correct password
			When the employee clicks the login button 
			Then 	the manager should be logged in to the manager homepage 
	
Feature: Managers should be able to create and assign defects
	
	Scenario: As a manager I want to create and assign defects

			Given the manager is logged in to the manager homepage
			When the manager enters the defect name
			When the manager selects a tester
			When the manager clicks the assign button
			Then the new defect is created and assigned

	
Feature: Testers should be able to login to access their homepages.

	Scenario: As a tester I want to sign in so I can view my custom homepage. 
	
			Given the employee is on the login page
			When the tester enters his correct username
			When the tester enters his correct password
			When the employee clicks the login button 
			Then the tester should be logged in to the tester homepage 

Feature: Testers should be able to view defects assigned to them

	Scenario: As a tester I want to view defects assigned to me.
	
			Given the tester is logged in to the tester homepage
			Then the tester should be able to view defects assigned to them

Feature: Testers should be able to update defects.

	Scenario: As a tester I want to accept a defect

			Given the tester is logged in to the tester homepage
			When the tester selects the accept option
			When the tester clicks the update button
			Then the tester should accept the defect
