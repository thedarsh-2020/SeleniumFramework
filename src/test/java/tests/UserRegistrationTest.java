package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTest extends TestBase {

	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;

	@Test (priority = 1)
	public void UserCanRegisterSuccssfully()
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration("Anas", "Musta", "testmail@gmail.com", "12345678");
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
		loginObject.UserLogin("testmail@gmail.com", "12345678");
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}

}
