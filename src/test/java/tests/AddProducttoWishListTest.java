package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishListPage;

public class AddProducttoWishListTest extends TestBase
{
	SearchPage searchPage;
	ProductDetailsPage productDetails;
	WishListPage wishListObject;
	String productName = "Apple MacBook Pro";

	@Test(priority = 1)
	public void UserCanSearchForProductWithAutoSuggest() throws InterruptedException
	{
			searchPage = new SearchPage(driver);
			Thread.sleep(1000);
			searchPage.ProductSearchUsingAutoSuggest("Mac");
			productDetails = new ProductDetailsPage(driver);
			Assert.assertTrue(productDetails.productNamebreadCrumb.getText().contains(productName));
	}

	@Test(priority = 2)
	public void UserCanAddProductToWishList()
	{
		productDetails = new ProductDetailsPage(driver);
		productDetails.addProductToWishList();
		driver.navigate().to("https://demo.nopcommerce.com" + "/wishlist");
		wishListObject = new WishListPage(driver);
		Assert.assertTrue(wishListObject.wishListHeader.isDisplayed());
		Assert.assertTrue(wishListObject.productCell.getText().contains(productName));
	}

	@Test(priority = 3)
	public void UserCanRemoveProductFromWishList()
	{
		wishListObject = new WishListPage(driver);
		wishListObject.removeProductFromWishList();
		Assert.assertTrue(wishListObject.EmptyCartLbl.getText().contains("The wishlist is empty!"));
	}

}
