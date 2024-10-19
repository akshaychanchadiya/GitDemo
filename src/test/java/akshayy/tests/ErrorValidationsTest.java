package akshayy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import akshayy.TestComponents.BaseTest;
import akshayy.TestComponents.Retry;
import akshayy.pageobjects.CartPage;
import akshayy.pageobjects.CheckOutPage;
import akshayy.pageobjects.ConfirmationPage;
import akshayy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

        @Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
        public void LoginErrrorValidation() throws IOException
        {
        	
		landingPage.loginApplication("akshaychanchadiya31@gmail.com","Akshay23");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
        
        @Test
        public void ProductErrorValidation() throws IOException
        {
        	
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("akshaychanchadiya31@gmail.com","Akshay@123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
	    Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
        }

}
