package akshayy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import akshayy.TestComponents.BaseTest;
import akshayy.pageobjects.CartPage;
import akshayy.pageobjects.CheckOutPage;
import akshayy.pageobjects.ConfirmationPage;
import akshayy.pageobjects.LandingPage;
import akshayy.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_In_username_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();

		checkOutPage.selectCountry("India");
		confirmationPage = checkOutPage.submitOrder();
	}

	// Then "THANKYOU FOR THE ORDER." message is dsiplayed on ConfirmationPage

	@Then("{string} message is dsiplayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String cnfrmMsg = confirmationPage.getConfirmationMsg();
		Assert.assertTrue(cnfrmMsg.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("{string} message is displayed")
	public void incorrectEmailOrPasswordMessageIsDisplayed(String string) {

		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}

}
