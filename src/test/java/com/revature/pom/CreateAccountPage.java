package com.revature.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage {
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
	
	public Select gender;
	
	@FindBy(xpath = "/html/body/div/form/button")
	public WebElement createAccount;
	
	@FindBy(xpath = "/html/body/div/form/a")
	public WebElement back;
	
	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}


