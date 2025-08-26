package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver)
	{
		super(driver);
		jse = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}

	// '@' is annotation, w hna ana b2ol ma aktb "registerLink" yb2a ro7 dwr 3liha fe el annotation
	@FindBy(linkText = "Register")
	WebElement registerLink;

	@FindBy(linkText = "Log in")
	WebElement loginLink;

	@FindBy(linkText = "Contact us")
	WebElement contactUsLink;

	@FindBy(id = "customerCurrency")
	WebElement currencydrl;
	
	@FindBy(xpath = "/html/body/div[6]/div[2]/ul[1]/li[1]/a")
	WebElement ComputerMenu;
	
	@FindBy(xpath = "/html/body/div[6]/div[2]/ul[1]/li[1]/ul/li[2]/a")
	WebElement NotebooksMenu;

	public void openRegistrationPage()
	{
		clickButton(registerLink);
	}

	public void openLoginPage()
	{
		clickButton(loginLink);
	}

	public void openContactUsPage()
	{
		scrollToBottom();
		clickButton(contactUsLink);
	}

	public void changeCurrency()
	{
		select = new Select(currencydrl);
		select.selectByVisibleText("Euro");
	}
	
	public void selectNoteBooksMenu()
	{
		action.moveToElement(ComputerMenu).moveToElement(NotebooksMenu).click().build().perform();
	}

}
