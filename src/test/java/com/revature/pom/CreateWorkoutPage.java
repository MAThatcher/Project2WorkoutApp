package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateWorkoutPage {

	public WebDriver driver;

	@FindBy(xpath = "/html/body/nav/div/a")
	public WebElement home;

	@FindBy(xpath = "/html/body/nav/div/div/form/a[1]")
	public WebElement information;

	@FindBy(xpath = "/html/body/nav/div/div/form/a[2]")
	public WebElement editAccount;

	@FindBy(xpath = "/html/body/nav/div/div/form/a[3]")
	public WebElement Logout;
	
	@FindBy(xpath = "//*[@id=\"workoutName\"]")
	public WebElement workoutName;
	
	public Select workoutType;
	
	public Select exerciseType;
	
	public Select listOfExercises;
	
	@FindBy(xpath = "//*[@id=\"sets\"]")
	public WebElement sets;
	
	@FindBy(xpath = "//*[@id=\"reps\"]")
	public WebElement reps;
	
	@FindBy(xpath = "/html/body/div[2]/div[3]/div/div[1]/button")
	public WebElement addExercise;
	
	@FindBy(xpath = "/html/body/div[2]/div[3]/div/div[2]/button")
	public WebElement createWorkout;

	public CreateWorkoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
