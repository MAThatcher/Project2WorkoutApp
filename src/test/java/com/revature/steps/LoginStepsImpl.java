package com.revature.steps;

import org.openqa.selenium.WebDriver;

import com.revature.pom.LoginPage;
import com.revature.runners.LoginRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class LoginStepsImpl {
	public static LoginPage login = LoginRunner.login;
	public static WebDriver driver = LoginRunner.driver;


	@Given("^The user is on the Login page$")
	public void the_user_is_on_the_Login_page() throws Throwable {
		driver.get("http://localhost:8080/login.html");
	}

	@When("^The user enters his username$")
	public void the_user_enters_his_username() throws Throwable {
		login.email.sendKeys("admin");
	}

	@When("^The user enters his password$")
	public void the_user_enters_his_password() throws Throwable {
		login.password.sendKeys("password");
	}

	@When("^The user clicks on log in$")
	public void the_user_clicks_on_log_in() throws Throwable {
		login.login.click();
		Thread.sleep(5000);
	}

	@Then("^The user should be on his Home Page$")
	public void the_user_should_be_on_his_Home_Page() throws Throwable {
		Assert.assertEquals("Track Main Page", driver.getTitle());
		System.out.println("Login feature done");
		
	}

}
