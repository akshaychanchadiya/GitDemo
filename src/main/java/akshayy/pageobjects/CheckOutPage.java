package akshayy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import akshayy.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	private WebElement selectCountry;
	
	@FindBy(css=".btnn")
	private WebElement submit;
	
	By result = By.cssSelector(".ta-results");
	
	//driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
	//driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	//driver.findElement(By.cssSelector(".btnn")).click();
	
	public void selectCountry(String countryName)
	{
		country.sendKeys(countryName);
		waitForElementToAppear(result);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
}
