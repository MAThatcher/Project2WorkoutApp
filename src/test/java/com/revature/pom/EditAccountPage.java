package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditAccountPage {
	public WebDriver driver;

	@FindBy(id = "fname")
	public WebElement fname;

	@FindBy(id = "lname")
	public WebElement lname;

	@FindBy(id = "username")
	public WebElement username;

	@FindBy(id = "email")
	public WebElement email;

	@FindBy(id = "password")
	public WebElement password1;

	@FindBy(id = "password2")
	public WebElement password2;

	@FindBy(id = "phone")
	public WebElement phNumber;

	@FindBy(id = "age")
	public WebElement age;

	@FindBy(id = "weight")
	public WebElement weight;

	@FindBy(id = "goalweight")
	public WebElement goalWeight;

	@FindBy(id = "height")
	public WebElement height;

	@FindBy(id = "time")
	public WebElement time;

	@FindBy(id = "gender")
	public WebElement gender;

	@FindBy(xpath = "/html/body/div/form/button")
	public WebElement updateAccount;

	@FindBy(xpath = "/html/body/div/form/a")
	public WebElement back;
	
	@FindBy(xpath = "/html/body/nav/div/a")
	public WebElement home;
	
	@FindBy(xpath = "/html/body/nav/div/div/form/a[1]")
	public WebElement information;
	
	@FindBy(xpath = "/html/body/nav/div/div/form/a[2]")
	public WebElement Logout;
	
	public EditAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
