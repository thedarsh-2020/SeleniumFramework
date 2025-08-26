package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends PageBase
{
	public WishListPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(css = "td.product")
	public WebElement productCell;

	@FindBy(css = "h1")
	public WebElement wishListHeader;

	@FindBy(name = "updatecart")
	WebElement updateWishListBtn;

	@FindBy(css = "button.remove-btn")
	WebElement removeFromCartCheck;

	@FindBy(css = "div.no-data")
	public WebElement EmptyCartLbl;

	public void removeProductFromWishList()
	{
		clickButton(removeFromCartCheck);
	}

}
