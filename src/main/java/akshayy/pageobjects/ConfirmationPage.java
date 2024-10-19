package akshayy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import akshayy.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confrmMsg;
	
	
	//String cnfrmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	public String getConfirmationMsg()
	{
		return confrmMsg.getText();
	}

}
