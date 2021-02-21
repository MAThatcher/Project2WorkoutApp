package com.revature.runners;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.revature.pom.CreateAccountPage;
import com.revature.pom.CreateWorkoutPage;
import com.revature.pom.HomePage;
import com.revature.pom.LoginPage;

public class Populate {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;

		File file = new File("src/test/resources/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		driver = new FirefoxDriver();
		LoginPage login = new LoginPage(driver);
		CreateAccountPage create = new CreateAccountPage(driver);
		HomePage home = new HomePage(driver);
		CreateWorkoutPage createP = new CreateWorkoutPage(driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/createaccount.html");
		Select gender = new Select(driver.findElement(By.id("gender")));

		for (int i = 0; i < 100; i++) {
			try {
				int theme;
				int exerciseType;
				driver.get("http://localhost:8080/createaccount.html");

				create.fname.sendKeys("Nick");
				create.lname.sendKeys("Ungar");

				String username = "Nick.Ungar" + String.valueOf((long) (Math.random() * 50000000));
				create.username.sendKeys(username);

				// generate random email
				String email = username + "@email.com";
				create.email.sendKeys(email);
				String password = "password";
				create.password1.sendKeys(password);
				create.password2.sendKeys(password);

				create.phNumber.sendKeys(String.valueOf((long) (Math.random() * 100000 * 100000)));

				int age = (int) (Math.random() * 75 + 15);
				create.age.sendKeys(String.valueOf(age));

				int weight = (int) ((Math.random() * 150) + 150);
				create.weight.sendKeys(String.valueOf(weight));
				create.goalWeight.sendKeys(String.valueOf(weight - 50));

				// give a height between 5 feet and 6 foot 6 in inches
				int height = ((int) (Math.random() * 18) + 60);
				create.height.sendKeys(String.valueOf(height));

				create.time.sendKeys(String.valueOf((int) (Math.random() * 50)));
				int genders = (int) (Math.random() * 3);

				// Intentionally favoring creating male owned accounts for the purposes of etl
				// testing

				create.gender = new Select(driver.findElement(By.id("gender")));
				switch (genders) {
				case 0:
					create.gender.selectByIndex(2);
					break;
				case 1:
					create.gender.selectByIndex(2);
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
				Thread.sleep(1000);

				create.createAccount.click();
				Thread.sleep(1000);

				driver.get("http://localhost:8080/login.html");
				login.email.sendKeys(username);
				login.password.sendKeys(password);
				login.login.click();
				Thread.sleep(500);

				home.createWorkout.click();
				Thread.sleep(500);

				int numExercises = (int) (Math.random() * 3) + 1;
				int numWorkouts = (int) (Math.random() * 3) + 1;
				for (int k = 0; k < numWorkouts; k++) {
					createP.workoutType = new Select(driver.findElement(By.xpath("//*[@id=\"workoutType\"]")));
					createP.exerciseType = new Select(driver.findElement(By.xpath("//*[@id=\"exerciseType\"]")));
					createP.listOfExercises = new Select(driver.findElement(By.xpath("//*[@id=\"exercises\"]")));
					createP.workoutName.sendKeys("Selenium aided workout" + String.valueOf(k + 1));
					theme = (int) (Math.random() * 7) + 1;
					createP.workoutType.selectByIndex(theme);
					for (int j = 0; j < numExercises; j++) {

						exerciseType = (int) (Math.random() * 3) + 1;
						createP.exerciseType.selectByIndex(exerciseType);

						// give time to retrieve the exercise lists
						Thread.sleep(2000);

						int x = 0;
						switch (exerciseType) {
						case 1:
							x = (int) (Math.random() * 4);
							createP.listOfExercises.selectByIndex(x);
							break;
						case 2:

							x = (int) (Math.random() * 17);
							createP.listOfExercises.selectByIndex(x);
							break;
						case 3:

							x = (int) (Math.random() * 16);
							createP.listOfExercises.selectByIndex(x);
							break;
						default:
							break;
						}

						createP.sets.sendKeys(String.valueOf((int) (Math.random() * 4) + 1));

						createP.reps.sendKeys(String.valueOf((int) (Math.random() * 4) + 1));

						createP.addExercise.click();
						Thread.sleep(1000);
					}
					createP.createWorkout.click();
					Thread.sleep(2000);
					driver.switchTo().alert().accept();
					driver.get("http://localhost:8080/createworkout.html");
				}
			} catch (Exception e) {
				e.printStackTrace();
				driver.close();
				break;
			}

		}
		driver.close();
	}

}
