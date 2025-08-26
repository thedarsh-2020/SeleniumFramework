package dataDrivenTests;

import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegisterationTestWithDDTusingCSV extends TestBase
{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;

	CSVReader reader;

	@Test
	public void UserCanRegisterSuccssfullyUsingCSV() throws CsvValidationException, IOException
	{
		// get path of CSV file
		String CSV_file = System.getProperty("user.dir") + "/src/test/java/testFiles/UserData.csv";
		reader = new CSVReader(new FileReader(CSV_file));

		String[] csvCell;

		// while loop will be executed till last value in CSV file
		while( (csvCell = reader.readNext()) != null)
		{
			String firstName	= csvCell[0];
			String lastName		= csvCell[1];
			String email		= csvCell[2];
			String password		= csvCell[3];

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
}
