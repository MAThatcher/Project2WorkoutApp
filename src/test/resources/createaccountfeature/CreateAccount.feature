Feature: Create Account fucntionality works

	Scenario: Creating A user account
		Given The user starts on the Login page
		When The user clicks on create account that is on the login page
		When The user enters all his information into create account form
		When The user clicks on create account button to create a new account
		Then The user should be on the login page