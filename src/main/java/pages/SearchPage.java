package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageBase{

	public SearchPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy (id = "small-searchterms")
	WebElement searchTextBox;
	
	@FindBy (css = "button.button-1.search-box-button")
	WebElement searchBtn;
	
	@FindBy (id = "ui-id-1")
	List<WebElement> productList;
	
	@FindBy (linkText = "Apple MacBook Pro")
	WebElement productTitle;
	
	public void ProductSearch(String productName)
	{
		setTextElementText(searchTextBox, productName);
		clickButton(searchBtn);
	}
	
	public void OpenProductDetails()
	{
		clickButton(productTitle);
	}
	
	public void ProductSearchUsingAutoSuggest(String searchTxt)
	{
		setTextElementText(searchTextBox, searchTxt);
		productList.get(0).click();
	}
	
}
