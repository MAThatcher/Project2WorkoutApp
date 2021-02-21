package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;
	
	@FindBy(id = "email")
	public WebElement email;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(xpath = "/html/body/form/a[1]")
	public WebElement login;

	@FindBy(xpath = "/html/body/form/a[2]")
	public WebElement createaccount;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
