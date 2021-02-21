Feature: Edit Account fucntionality works

	Scenario: Editing a user account
		Given The user has all ready logged in
		When The user clicks on edit account
		When The user changes their goal weight to 200
		Then The user should see alert box confirming the account has been changed
		