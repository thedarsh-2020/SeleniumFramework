package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageBase {

	protected WebDriver driver;
	public JavascriptExecutor jse;
	public Select select;
	public Actions action;

	//create a constructor with driver parameter
	public PageBase(WebDriver driver)
	{
		//this is a keyword to tell java take the object from current class
		PageFactory.initElements(driver, this);
	}

	protected static void clickButton(WebElement button)
	{
		button.click();
	}
	
	protected static void setTextElementText(WebElement textElement, String value)
	{
		textElement.sendKeys(value);
	}

	public void scrollToBottom()
	{
		jse.executeScript("scrollBy(0, 2500)");
	}
	
	public void clearText(WebElement elemnt)
	{
		elemnt.clear();
	}
	
}
