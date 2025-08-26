package stepsWithE2ETests;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckoutPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import tests.TestBase;

public class E2ETests extends TestBase
{
	SearchPage searchObject;
	ProductDetailsPage productDetails;
	ShoppingCartPage cartObject;
	CheckoutPage checkoutObject;
	OrderDetailsPage orderObject;
	String productName = "Apple MacBook Pro";

	@Given("The user in the Home Page")
	public void the_user_in_the_home_page()
	{
		Assert.assertTrue(driver.getCurrentUrl().contains("demo.nopcommerce.com"));
	}

	@When("He search for {string}")
	public void he_search_for(String productName)
	{
		searchObject = new SearchPage(driver);
		searchObject.ProductSearchUsingAutoSuggest(productName);
		productDetails = new ProductDetailsPage(driver);
		Assert.assertTrue(productDetails.productNamebreadCrumb.getText().contains(productName));
	}
	
	@When("choose to two items")
	public void choose_to_two_items()
	{
		cartObject = new ShoppingCartPage(driver);
		productDetails.AddToCart();
		driver.navigate().to("http://demo.nopcommerce.com/" + "cart");
	}

	@When("moves to checkout cart and enter personal details on checkout page and place the order")
	public void moves_to_checkout_cart_and_enter_personal_details_on_checkout_page_and_place_the_order() throws InterruptedException
	{
		checkoutObject = new CheckoutPage(driver);
		cartObject.openCheckoutPageAsGuest();
		checkoutObject.GuestCheckoutProduct("test", "user", "Egypt", "Cairo"
				, "testuser1@test.com", "test address", "123456", "32445566677", "Cairo", productName);
		Assert.assertTrue(checkoutObject.productName.isDisplayed());
		Assert.assertTrue(checkoutObject.productName.getText().contains(productName));
		checkoutObject.confirmOrder();
		Assert.assertTrue(checkoutObject.ThankYouLbl.isDisplayed());
	}

	@Then("He can view the order and download the invoice")
	public void he_can_view_the_order_and_download_the_invoice() throws InterruptedException
	{
		orderObject = new OrderDetailsPage(driver);
		checkoutObject.viewOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObject.DownloadPDFInvoice();
		Thread.sleep(3000);
		orderObject.PrintOrderDetails();
	}
}
