package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class AddProuctToShoppingCartTest extends TestBase
{
	SearchPage searchPage;
	ProductDetailsPage productDetails;
	ShoppingCartPage cartPage;
	String productName = "Apple MacBook Pro";
	
	@Test(priority = 1)
	public void UserCanSearchForProductWithAutoSuggest() throws InterruptedException
	{
		searchPage = new SearchPage(driver);
		searchPage.ProductSearchUsingAutoSuggest("Mac");
		Thread.sleep(1000);
		productDetails = new ProductDetailsPage(driver);
		Assert.assertTrue(productDetails.productNamebreadCrumb.getText().contains(productName));
	}
	
	@Test(priority = 2)
	public void UserCanAddProductToShoppingCart() throws InterruptedException
	{
		productDetails = new ProductDetailsPage(driver);
		productDetails.AddToCart();
		Thread.sleep(1000);
		driver.navigate().to("https://demo.nopcommerce.com" + "/cart");
		cartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(cartPage.totalLbl.getText().contains("3,600"));
	}
	
	@Test(priority = 3)
	public void UserCanRemoveProductFromCart()
	{
		cartPage = new ShoppingCartPage(driver);
		cartPage.RemoveProductFromCart();
		Assert.assertTrue(cartPage.EmptyCartLbl.getText().contains("Your Shopping Cart is empty!"));
	}

}
