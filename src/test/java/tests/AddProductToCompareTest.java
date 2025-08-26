package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ComparePage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class AddProductToCompareTest extends TestBase
{
	ProductDetailsPage detailsObject;
	HomePage homeObject;
	ComparePage compareObject;
	SearchPage searchObject;	

	String firstProductName = "Apple MacBook Pro";
	String secondProductName = "Asus Laptop";

	@Test(priority = 1)
	public void UserCanCompareProducts() throws InterruptedException
	{
		searchObject = new SearchPage(driver);
		detailsObject = new ProductDetailsPage(driver);
		compareObject = new ComparePage(driver);
		Thread.sleep(1000);

		// 1- search for product number 1
		searchObject.ProductSearchUsingAutoSuggest("Mac");
		Assert.assertTrue(detailsObject.productNamebreadCrumb.getText().contains(firstProductName));
		detailsObject.AddProductToCompare();
		Thread.sleep(1000);

		// 2- search for product number 2
		searchObject.ProductSearchUsingAutoSuggest("Asus");
		Assert.assertTrue(detailsObject.productNamebreadCrumb.getText().contains(secondProductName));
		detailsObject.AddProductToCompare();
		Thread.sleep(1000);

		// 3- add to compare
		driver.navigate().to("https://demo.nopcommerce.com" + "/compareproducts");
		Assert.assertTrue(compareObject.firstProductName.getText().equals("Apple MacBook Pro"));
		Assert.assertTrue(compareObject.secondProductName.getText().equals("Asus Laptop"));
		compareObject.CompareProducts();
	}

	// 4- clear list
	@Test(priority = 2)
	public void UserCanClearCompareList()
	{
		compareObject.clearCompareList();
		Assert.assertTrue(compareObject.noDataLbl.getText().contains("You have no items to compare."));
	}

}
