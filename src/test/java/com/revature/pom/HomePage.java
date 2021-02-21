package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public WebDriver driver;

	@FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/a")
	public WebElement createWorkout;

	@FindBy(xpath = "/html/body/div[2]/div[1]/div[2]/a")
	public WebElement viewAnalysis;

	@FindBy(xpath = "/html/body/div[2]/div[2]/div[1]/a")
	public WebElement editProgress;

	@FindBy(xpath = "/html/body/div[2]/div[2]/div[2]/a")
	public WebElement createExercise;
	
	@FindBy(xpath = "/html/body/nav/div/a")
	public WebElement home;
	
	@FindBy(xpath = "/html/body/nav/div/div/form/a[1]")
	public WebElement information;
	
	@FindBy(xpath = "/html/body/nav/div/div/form/a[2]")
	public WebElement editAccount;
	
	@FindBy(xpath = "/html/body/nav/div/div/form/a[3]")
	public WebElement Logout;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
