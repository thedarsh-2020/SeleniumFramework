package dataDrivenTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegisterationTestWithDDTusingExcel extends TestBase
{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	
	@DataProvider(name = "ExcelData")
	public Object[][] userRegisterData() throws IOException
	{
		// get data from ExcelReader class
		ExcelReader ER = new ExcelReader();
		return ER.getExcelData();
		
	}

	@Test(dataProvider = "ExcelData")
	public void UserCanRegisterSuccssfullyUsingExcel(String firstName, String lastName, String email, String password)
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, email, password);
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
