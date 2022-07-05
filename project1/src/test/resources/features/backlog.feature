Feature: Testers should be able create projects, summaries, and cases

	Scenario: As a tester I want to create a project

		Given the tester is logged in to the tester homepage
		When the tester enters a project title
		When the tester enters info about the project
		When the tester clicks the create project button
		Then the API receives the command

	Scenario: As a tester I want to create a test summary report and attach them to a project

		Given the tester is logged in to the tester homepage
		When the tester selects a project to attach the test summary report to
		When the tester enters a reason for testing
		When the tester clicks the create summary button
		Then the API receives the command 

	Scenario: As a tester I want to create a test case and attach it to a test summary report

		Given the tester is logged in to the tester homepage
		When the tester selects a test summary report to attach the test case to
		When the tester enters the feature tested
		When the tester selects a result
		When the testers clicks the create test case button
		Then the API receives the command

	#Scenario: As a tester I want to delete a test case
		#Given a test case exists 
		#Given the tester is logged in to the tester homepage
		#When the tester clicks the delete test case button
		#Then the API receives the command

