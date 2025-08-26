package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends PageBase
{
	public ShoppingCartPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(name = "updatecart")
	WebElement removeCheck;

	@FindBy(id = "open-estimate-shipping-popup")
	WebElement updateCartBtn;

	@FindBy(id = "itemquantity2")
	public WebElement quantityTxt;

	@FindBy(css = "span.product-subtotal")
	public WebElement totalLbl;
	
	@FindBy(css = "div.no-data")
	public WebElement EmptyCartLbl;
	
	@FindBy(id = "checkout")
	WebElement checkoutBtn;
	
	@FindBy(id = "termsofservice")
	WebElement agreeCheckBox;
	
	@FindBy(css="button.button-1.checkout-as-guest-button")
	WebElement guestCheckoutBtn;

	public void RemoveProductFromCart()
	{
		clickButton(removeCheck);
	}

	public void UpdateProductQuantityInCart(String quantity)
	{
		// clear quantity textbox
		clearText(quantityTxt);
		setTextElementText(quantityTxt, quantity);
		clickButton(updateCartBtn);
	}
	
	public void openCheckoutPage()
	{
		clickButton(agreeCheckBox);
		clickButton(checkoutBtn);
	}
	
	public void openCheckoutPageAsGuest() 
	{
		clickButton(agreeCheckBox);
		clickButton(checkoutBtn);
		clickButton(guestCheckoutBtn);
	}

}
