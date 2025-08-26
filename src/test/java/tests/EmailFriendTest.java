package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.EmailPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class EmailFriendTest extends TestBase
{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	String productName = "Apple MacBook Pro";
	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	EmailPage emailObject;
	
	// 1- user registeration
	@Test (priority = 1)
	public void UserCanRegisterSuccssfully()
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration("Anas", "Musta", "test28@gmail.com", "12345678");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
	
	// 2- search for product
	@Test (priority = 2)
	public void UserCanSearchWithAutoSuggest()
	{
		try {
			searchObject = new SearchPage(driver);
			searchObject.ProductSearchUsingAutoSuggest("Mac");
			detailsObject = new ProductDetailsPage(driver);
			Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(), productName);
		} catch (Exception e) {
			System.out.println("Error Occured: " + e.getMessage());
		}
	}
	
	// 3- email to friend
	@Test (priority = 3)
	public void RegisterUserCanSendProductToFriend()
	{
		//detailsObject = new ProductDetailsPage(driver);
		detailsObject.openSendEmail();
		emailObject = new EmailPage(driver);
		emailObject.SendEmailToFriend("test@gmail.com", "Hello My Friend, Check this product");
		Assert.assertTrue(emailObject.messageNotification.getText().contains("Your message has been sent."));
	}
	
	// 4- user Logout
	@Test (priority = 4)
	public void RegisterUserCanLogout()
	{
		registerObject.userLogout();
	}
	
}
