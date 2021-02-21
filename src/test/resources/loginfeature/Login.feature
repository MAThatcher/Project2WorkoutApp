Feature: Login fucntionality works

	Scenario: Logging in as a user
		Given The user is on the Login page
		When The user enters his username
		When The user enters his password
		When The user clicks on log in
		Then The user should be on his Home Page
		