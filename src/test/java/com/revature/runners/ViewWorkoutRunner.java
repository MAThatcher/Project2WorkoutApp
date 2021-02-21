package com.revature.runners;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.revature.pom.CreateAccountPage;
import com.revature.pom.CreateWorkoutPage;
import com.revature.pom.EditAccountPage;
import com.revature.pom.HomePage;
import com.revature.pom.LoginPage;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/viewworkoutfeature", glue = "com.revature.steps")
public class ViewWorkoutRunner {
	
	public static WebDriver driver;
	public static LoginPage login;
	public static HomePage home;
	public static EditAccountPage edit;
	public static CreateAccountPage create;
	public static CreateWorkoutPage createP;

	
	@BeforeClass
	public static void setUp() {
		File file = new File("src/test/resources/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		login = new LoginPage(driver);
		home = new HomePage(driver);
		edit = new EditAccountPage(driver);
		create = new CreateAccountPage(driver);
		createP = new CreateWorkoutPage(driver);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
