package akshayy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import akshayy.TestComponents.BaseTest;
import akshayy.pageobjects.CartPage;
import akshayy.pageobjects.CheckOutPage;
import akshayy.pageobjects.ConfirmationPage;
import akshayy.pageobjects.OrderPage;
import akshayy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
        @Test(dataProvider="getData",groups={"Purchase"})
        public void SubmitOrder(HashMap<String,String> input) throws IOException
        {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
	
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
	
	    Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		
		String cnfrmMsg=confirmationPage.getConfirmationMsg();
		Assert.assertTrue(cnfrmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
        
        @Test(dependsOnMethods= {"SubmitOrder"})
        public void OrderHistoryTest()
        {
        	ProductCatalogue productCatalogue = landingPage.loginApplication("akshaychanchadiya31@gmail.com","Akshay@123");
        	OrderPage orderPage = productCatalogue.goToOrderPage();
        	Boolean match = orderPage.verifyOrderDisplay(productName);
        	Assert.assertTrue(match);
        }
        
       
        
          @DataProvider
          public Object[][] getData() throws IOException
          {

        	  
        	  List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//akshayy//data//PurchaseOrder.json");
        	  return new Object[][] {{data.get(0)},{data.get(1)}};
          }
        
   //     @DataProvider
   //     public Object[][] getData()
   //     {
   //     	return new Object[][] {{"akshaychanchadiya31@gmail.com","Akshay@123","ZARA COAT 3"},{"akshay31@gmail.com","Akshay@123","ADIDAS ORIGINAL"}};
   //     }

//    	  HashMap<String,String> map = new HashMap<String,String>();
//    	  map.put("email","akshaychanchadiya31@gmail.com");
//    	  map.put("password","Akshay@123");
//    	  map.put("product","ZARA COAT 3");
//    	  
//    	  HashMap<String,String> map1 = new HashMap<String,String>();
//    	  map1.put("email","akshay31@gmail.com");
//    	  map1.put("password","Akshay@123");
//    	  map1.put("product","ADIDAS ORIGINAL");
}
