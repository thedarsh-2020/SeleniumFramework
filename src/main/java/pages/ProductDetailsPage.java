package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends PageBase{

	public ProductDetailsPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy (css = "strong.current-item")
	public WebElement productNamebreadCrumb;

	@FindBy (css = "button.button-2.email-a-friend-button")
	WebElement emailFriendBtn;

	@FindBy (css = "span.price-value-4")
	public WebElement productPricelbl;

	@FindBy (linkText = "Add your review")
	WebElement addReviewLink;

	@FindBy(id = "add-to-wishlist-button-4")
	WebElement addToWishListBtn;

	@FindBy(css = "button.button-2.add-to-compare-list-button")
	WebElement addToCopmareBtn;
	
	@FindBy(id = "add-to-cart-button-4")
	WebElement addToCartBtn;

	public void openSendEmail()
	{
		clickButton(emailFriendBtn);
	}

	public void openAddReviewPage()
	{
		clickButton(addReviewLink);
	}

	public void addProductToWishList()
	{
		clickButton(addToWishListBtn);
	}

	public void AddProductToCompare()
	{
		clickButton(addToCopmareBtn);
	}
	
	public void AddToCart()
	{
		clickButton(addToCartBtn);
	}

}
