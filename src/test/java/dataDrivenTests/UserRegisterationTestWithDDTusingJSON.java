package dataDrivenTests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import data.JsonDataReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegisterationTestWithDDTusingJSON extends TestBase
{	
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;

	@Test
	public void UserCanRegisterSuccssfullyUsingJSON() throws FileNotFoundException, IOException, ParseException
	{
		JsonDataReader jsonReader = new JsonDataReader();
		jsonReader.JsonReader();

		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(jsonReader.firstName, jsonReader.lastName, jsonReader.email, jsonReader.password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));

		// user logout
		registerObject.userLogout();


		// check login with registered mail
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(jsonReader.email, jsonReader.password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));

		// user logout
		registerObject.userLogout();
	}
}
