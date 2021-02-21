package com.revature.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.revature.pom.CreateWorkoutPage;
import com.revature.pom.HomePage;
import com.revature.pom.LoginPage;
import com.revature.runners.CreateWorkoutRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class CreateWorkoutStepsImpl {

	public static LoginPage login = CreateWorkoutRunner.login;
	public static WebDriver driver = CreateWorkoutRunner.driver;
	public static HomePage home = CreateWorkoutRunner.home;
	public static CreateWorkoutPage create = CreateWorkoutRunner.createP;

	public int theme;
	public int exerciseType;
	public int exerciseList;

	@Given("^The user starts by logging in$")
	public void the_user_starts_by_logging_in() throws Throwable {
		driver.get("http://localhost:8080/login.html");
		login.email.sendKeys("admin");
		login.password.sendKeys("password");
		login.login.click();
		Thread.sleep(1000);
	}

	@When("^The user navigates to create workout$")
	public void the_user_navigates_to_create_workout() throws Throwable {
		home.createWorkout.click();
		Thread.sleep(1000);
	}

	@When("^The user selects a workout type$")
	public void the_user_selects_a_workout_type() throws Throwable {
		create.workoutName.sendKeys("Selenium aided workout");
		create.workoutType = new Select(driver.findElement(By.xpath("//*[@id=\"workoutType\"]")));
		create.exerciseType = new Select(driver.findElement(By.xpath("//*[@id=\"exerciseType\"]")));
		create.listOfExercises = new Select(driver.findElement(By.xpath("//*[@id=\"exercises\"]")));

		theme = (int) (Math.random() * 7) + 1;
		create.workoutType.selectByIndex(theme);

		exerciseType = (int) (Math.random() * 3) + 1;
		create.exerciseType.selectByIndex(exerciseType);

		// give time to retrieve the exercise lists
		Thread.sleep(500);
	}

	@When("^The user selects an excercise type$")
	public void the_user_selects_an_excercise_type() throws Throwable {
		int x = 0;
		switch (exerciseType) {
		case 1:
			x = (int) (Math.random() * 4);
			create.listOfExercises.selectByIndex(x);
			break;
		case 2:

			x = (int) (Math.random() * 17);
			create.listOfExercises.selectByIndex(x);
			break;
		case 3:

			x = (int) (Math.random() * 16);
			create.listOfExercises.selectByIndex(x);
			break;
		default:
			break;
		}

	}

	@When("^The user enters sets$")
	public void the_user_enters_sets() throws Throwable {
		create.sets.sendKeys(String.valueOf((int) (Math.random() * 9) + 1));
	}

	@When("^The user enters reps$")
	public void the_user_enters_reps() throws Throwable {
		create.reps.sendKeys(String.valueOf((int) (Math.random() * 9) + 1));
	}

	@When("^The user clicks on add exercise$")
	public void the_user_clicks_on_add_exercise() throws Throwable {
		create.addExercise.click();
	}

	@When("^The user clicks on create workouts$")
	public void the_user_clicks_on_create_workouts() throws Throwable {
		create.createWorkout.click();
		Thread.sleep(2000);
	}

	@Then("^The user should see alert box$")
	public void the_user_should_see_alert_box() throws Throwable {
		Assert.assertEquals(driver.switchTo().alert().getText(), "Workout Created. Proud of you.");
	}
}
