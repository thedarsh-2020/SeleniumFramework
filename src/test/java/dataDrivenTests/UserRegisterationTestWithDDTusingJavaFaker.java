package dataDrivenTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegisterationTestWithDDTusingJavaFaker extends TestBase
{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	Faker fakeData = new Faker();

	String firstName	= fakeData.name().firstName();
	String lastName		= fakeData.name().lastName();
	String email		= fakeData.internet().emailAddress();
	String password		= fakeData.number().digits(8).toString();

	@Test
	public void UserCanRegisterSuccssfullyUsingFakeLibrary()
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, email, password);
		System.out.println("The User Data is: ");
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(password);
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
