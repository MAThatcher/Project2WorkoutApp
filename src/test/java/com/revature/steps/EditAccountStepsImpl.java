package com.revature.steps;

import org.openqa.selenium.WebDriver;

import com.revature.pom.CreateWorkoutPage;
import com.revature.pom.EditAccountPage;
import com.revature.pom.HomePage;
import com.revature.pom.LoginPage;
import com.revature.runners.EditAccountRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class EditAccountStepsImpl {
	
	public static LoginPage login = EditAccountRunner.login;
	public static WebDriver driver = EditAccountRunner.driver;
	public static HomePage home = EditAccountRunner.home;
	public static CreateWorkoutPage create = EditAccountRunner.createP;
	public static EditAccountPage edit = EditAccountRunner.edit;
	
	@Given("^The user has all ready logged in$")
	public void the_user_has_all_ready_logged_in() throws Throwable {
		driver.get("http://localhost:8080/login.html");
		login.email.sendKeys("admin");
		login.password.sendKeys("password");
		login.login.click();
		Thread.sleep(2000);
	}

	@When("^The user clicks on edit account$")
	public void the_user_clicks_on_edit_account() throws Throwable {
		home.editAccount.click();
		Thread.sleep(1000);
	}

	@When("^The user changes their goal weight to (\\d+)$")
	public void the_user_changes_their_goal_weight_to(int arg1) throws Throwable {
		edit.goalWeight.sendKeys(String.valueOf(arg1));
		edit.updateAccount.click();
	}

	@Then("^The user should see alert box confirming the account has been changed$")
	public void the_user_should_see_alert_box_confirming_the_account_has_been_changed() throws Throwable {
		Thread.sleep(1000);
		Assert.assertEquals(driver.switchTo().alert().getText(), "Account has been updated");
	}

}
