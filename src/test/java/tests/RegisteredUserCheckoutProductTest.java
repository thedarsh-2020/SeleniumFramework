package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.UserRegistrationPage;

public class RegisteredUserCheckoutProductTest extends TestBase
{
	// 1- Register user
	// 2- search for product
	// 3- Add to cart
	// 4- Checkout and Print invoice as PDF or print page
	// 5- Logout

	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 
	String productName = "Apple MacBook Pro"; 
	SearchPage searchObject ; 
	ProductDetailsPage detailsObject ;
	ShoppingCartPage cartPage ; 
	CheckoutPage checkoutObject ;
	OrderDetailsPage orderObject ;

	@Test(priority = 1)
	public void UserCanRegisterSuccssfully() 
	{
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver); 
		registerObject.userRegistration("Anas", "Musta", "test@gmail.com", "12345678");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}

	@Test(priority = 2)
	public void UserCanSearchWithAutoSuggest() 
	{
		try {
			searchObject = new SearchPage(driver); 
			searchObject.ProductSearchUsingAutoSuggest("MacB");
			detailsObject = new ProductDetailsPage(driver); 
			Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(), productName);
		} catch (Exception e) {
			System.out.println("Error occurred  " + e.getMessage());
		}
	}

	@Test(priority = 3)
	public void UserCanAddProductToShoppingCart() throws InterruptedException
	{
		detailsObject = new ProductDetailsPage(driver);
		detailsObject.AddToCart();
		Thread.sleep(1000);
		driver.navigate().to("http://demo.nopcommerce.com" + "/cart");	
		cartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(cartPage.totalLbl.getText().contains("3,600"));
	}

	@Test(priority = 4)
	public void UserCanCheckoutProduct() throws InterruptedException
	{
		checkoutObject = new CheckoutPage(driver);
		cartPage.openCheckoutPage();
		checkoutObject.RegisteredUserCheckoutProduct
		("Egypt", "test address", "123456", "32445566677", "Cairo", productName);
		Assert.assertTrue(checkoutObject.productName.isDisplayed());
		Assert.assertTrue(checkoutObject.productName.getText().contains(productName));
		checkoutObject.confirmOrder();
		Assert.assertTrue(checkoutObject.ThankYouLbl.isDisplayed());

		checkoutObject.viewOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObject = new OrderDetailsPage(driver); 
		orderObject.DownloadPDFInvoice();
		orderObject.PrintOrderDetails();
	}

	@Test(priority = 5)
	public void RegisteredUserCanLogout() 
	{
		registerObject.userLogout();
	}

}
