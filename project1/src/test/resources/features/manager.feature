Feature: Managers should be able to create and assign defects

	Scenario: As a manager I want to create and assign defects

		Given the manager is logged in to the manager homepage
		When the manager enters the defect name
		When the manager selects a tester
		When the manager clicks the assign button
		Then the API receives the command