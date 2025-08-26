package stepsWithDDT;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegisterationUsingBDDwithDDT extends TestBase
{
	HomePage homeObject;
	UserRegistrationPage registerObject;

	@Given("The user in the home page")
	public void the_user_in_the_home_page()
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
	}

	@When("I click on register link")
	public void i_click_on_register_link()
	{
		Assert.assertTrue(driver.getCurrentUrl().contains("register"));
	}

	@When("I entered {string}, {string}, {string}, {string}")
	public void i_entered(String firstName, String lastName, String email, String password)
	{
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, email, password);
	}

	@Then("The registeration page displayed successfully")
	public void the_registeration_page_displayed_successfully()
	{
		registerObject.userLogout();
	}
}
