package com.revature.steps;

import org.openqa.selenium.WebDriver;

import com.revature.pom.CreateWorkoutPage;
import com.revature.pom.HomePage;
import com.revature.pom.LoginPage;
import com.revature.runners.ViewWorkoutRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class ViewWorkoutStepsImpl {
	
	public static LoginPage login = ViewWorkoutRunner.login;
	public static WebDriver driver = ViewWorkoutRunner.driver;
	public static HomePage home = ViewWorkoutRunner.home;
	public static CreateWorkoutPage create = ViewWorkoutRunner.createP;
	
	@Given("^The user first logs in$")
	public void the_user_first_logs_in() throws Throwable {
		driver.get("http://localhost:8080/login.html");
		login.email.sendKeys("admin");
		login.password.sendKeys("password");
		login.login.click();
		Thread.sleep(2000);
	}

	@When("^The user clicks on view analysis$")
	public void the_user_clicks_on_view_analysis() throws Throwable {
		home.viewAnalysis.click();
		Thread.sleep(2000);
	}

	@Then("^The user is on the view workoutpage$")
	public void the_user_is_on_the_view_workoutpage() throws Throwable {
		Assert.assertEquals("Analysis Page", driver.getTitle());
	}

}
