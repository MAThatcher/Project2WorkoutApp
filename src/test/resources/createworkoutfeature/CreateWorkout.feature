Feature: Create workout fucntionality works

	Scenario: Create a workout
		Given The user starts by logging in
		When The user navigates to create workout
		When The user selects a workout type
		When The user selects an excercise type
		When The user enters sets
		When The user enters reps
		When The user clicks on add exercise
		When The user clicks on create workouts
		Then The user should see alert box
		