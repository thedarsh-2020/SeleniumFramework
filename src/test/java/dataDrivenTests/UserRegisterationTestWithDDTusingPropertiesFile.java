package dataDrivenTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.LoadProperties;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegisterationTestWithDDTusingPropertiesFile extends TestBase
{
	HomePage homeObject; 
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	String firstname = LoadProperties.userData.getProperty("firstname");
	String lastname = LoadProperties.userData.getProperty("lastname");
	String email = LoadProperties.userData.getProperty("email");
	String password = LoadProperties.userData.getProperty("password");

	@Test (priority = 1)
	public void UserCanRegisterSuccssfullyUsingPropertiesFile()
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstname, lastname, email, password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}

	@Test (dependsOnMethods = {"UserCanRegisterSuccssfully"})
	public void RegisteredUserCanLogout()
	{
		registerObject.userLogout();
	}

	@Test (dependsOnMethods = {"RegisteredUserCanLogout"})
	public void RegisteredUserCanLogin()
	{
		homeObject.openLoginPage();

		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email, password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}
}
