package com.revature.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.revature.pom.CreateAccountPage;
import com.revature.pom.LoginPage;
import com.revature.runners.CreateAccountRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class CreateAccountStepsImpl {

	public static CreateAccountPage create = CreateAccountRunner.create;
	public static LoginPage login = CreateAccountRunner.login;
	public static WebDriver driver = CreateAccountRunner.driver;

	@Given("^The user starts on the Login page$")
	public void the_user_starts_on_the_Login_page() throws Throwable {
		driver.get("http://localhost:8080/login.html");
	}

	@When("^The user clicks on create account that is on the login page$")
	public void the_user_clicks_on_create_account_that_is_on_the_login_page() throws Throwable {

		login.createaccount.click();
		Thread.sleep(1500);
	}

	@When("^The user enters all his information into create account form$")
	public void the_user_enters_all_his_information_into_create_account_form() throws Throwable {

		create.fname.sendKeys("Mitchell");
		create.lname.sendKeys("Thatcher");

		String username = "Mitchell.Thatcher" + String.valueOf((int) (Math.random() * 50000));
		create.username.sendKeys(username);

		// generate random email
		String email = username + "@email.com";
		create.email.sendKeys(email);

		create.password1.sendKeys("password");
		create.password2.sendKeys("password");

		create.phNumber.sendKeys(String.valueOf((int) (Math.random() * 100000 * 100000)));

		int age = (int) (Math.random() * 50 + 20);
		create.age.sendKeys(String.valueOf(age));

		int weight = (int) ((Math.random() * 300) + 150);
		create.weight.sendKeys(String.valueOf(weight));
		create.goalWeight.sendKeys(String.valueOf(weight - 50));

		// give a height between 5 feet and 6 foot 6 in inches
		int height = ((int) (Math.random() * 18) + 60);
		create.height.sendKeys(String.valueOf(height));

		create.time.sendKeys(String.valueOf((int)(Math.random() * 50)));
		int gender = (int) (Math.random() * 3);
		
		// Intentionally favoring creating male owned accounts for the purposes of etl
		// testing

		create.gender = new Select(driver.findElement(By.id("gender")));
		switch (gender) {
		case 0:
			create.gender.selectByIndex(2);
			break;
		case 1:
			create.gender.selectByIndex(1);
			break;
		case 2:
			create.gender.selectByIndex(1);
			break;
		case 3:
			create.gender.selectByIndex(2);
			break;
		default:
			create.gender.selectByIndex(2);
			break;
		}
		Thread.sleep(5000);
	}

	@When("^The user clicks on create account button to create a new account$")
	public void the_user_clicks_on_create_account_button_to_create_a_new_account() throws Throwable {
		create.createAccount.click();
	}

	@Then("^The user should be on the login page$")
	public void the_user_should_be_on_the_login_page() throws Throwable {
		Thread.sleep(3000);
		Assert.assertEquals("Track Login", driver.getTitle());
		System.out.println("Create Account Feature done");
	}

}
