package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsTest extends TestBase {
	
	HomePage home;
	ContactUsPage contactPage;
	
	String email = "test@gmail.com";
	String fullName = "Anas Mustafa";
	String enquiry = "Hello Admin, this is for Test";
	
	@Test
	public void UserCanUseContactUs()
	{
		home = new HomePage(driver);
		home.openContactUsPage();
		contactPage = new ContactUsPage(driver);
		contactPage.ContactUs(fullName, email, enquiry);
		Assert.assertTrue(contactPage.successMesage.getText()
				.contains("Your enquiry has been successfully sent to the store owner."));
	}

}
