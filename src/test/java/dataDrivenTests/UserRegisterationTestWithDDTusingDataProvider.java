package dataDrivenTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegisterationTestWithDDTusingDataProvider extends TestBase
{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;

	@DataProvider(name = "testData")
	public static Object[][] userData()
	{
		return new Object[][]
				{
			{"Anas", "Musta", "testUser003@gmail.com", "123456"},
			{"Must", "Anass", "testUser004@gmail.com", "123456"}
				};
	}

	@Test (dataProvider = "testData")
	public void UserCanRegisterSuccssfullyUsingDataProvider(String fname, String lname, String email, String password)
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(fname, lname, email, password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));

		// user logout
		registerObject.userLogout();

		// check login with registered mail
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email, password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));

		// user logout
		registerObject.userLogout();
	}

}
