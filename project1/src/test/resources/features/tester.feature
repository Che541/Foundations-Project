Feature: Testers should be able to view and update defects

	Scenario: As a tester I want to view defects assigned to me

		Given the tester is logged in to the tester homepage
		Then the tester should be able to view defects assigned to them

	Scenario: As a tester I want to accept a defect
		Given the tester has a pending defect
		Given the tester is logged in to the tester homepage
		When the tester selects the accept option
		When the tester clicks the update button
		Then the tester should accept the defect

	Scenario: As a tester I want to decline a defect
		Given the tester has a pending defect
		Given the tester is logged in to the tester homepage
		When the tester selects the decline option
		When the tester clicks the update button
		When the tester should decline the defect

	Scenario: As a tester I want to reject a defect
		Given the tester has an accepted defect
		Given the tester is logged in to the tester homepage
		When the tester selects the reject option
		When the tester clicks the update button
		Then the tester should reject the defect

	Scenario: As a tester I want to declare a defect as fixed
		Given the tester has an accepted defect
		Given the tester is logged in to the tester homepage
		When the tester selects the fixed option
		When the tester clicks the update button
		Then the tester should declare the defect as fixed

	Scenario: As a tester I want to shelve a defect
		Given the tester has an accepted defect
		Given the tester is logged in to the tester homepage
		When the tester selects the shelved option
		When the tester clicks the update button
		Then the tester should shelve the defect

