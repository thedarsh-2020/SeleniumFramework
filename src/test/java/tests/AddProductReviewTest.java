package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ProductReviewPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class AddProductReviewTest extends TestBase
{
	ProductReviewPage reviewObject;
	HomePage homeObject;
	UserRegistrationPage registerObject;
	String productName = "Apple MacBook Pro";
	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	
	// 1- user Reistration
	@Test (priority = 1)
	public void UserCanRegisterSuccssfully()
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration("Anas", "Musta", "testmail@gmail.com", "12345678");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
	
	// 2- Search for Product
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
	
	// 3- Add Review
	@Test (priority = 3)
	public void RegisteredUserCanReviewProduct()
	{
		detailsObject.openAddReviewPage();
		reviewObject = new ProductReviewPage(driver);
		reviewObject.AddProductReview("new Review", "Product is Good");
		Assert.assertTrue(reviewObject.reviewNotification.getText()
				.contains("Product review is successfully added"));
	}
	
	// 4- Logout
	@Test (priority = 4)
	public void RegisteredUserCanLogout()
	{
		registerObject.userLogout();
	}
	
}
