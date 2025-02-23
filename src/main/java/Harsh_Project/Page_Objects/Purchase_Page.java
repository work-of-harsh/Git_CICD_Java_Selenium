package Harsh_Project.Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Harsh_Project.AbstractComponents.AbstractComponent;

public class Purchase_Page extends AbstractComponent {
	
	//All comments to understand page object model is present on "Form_Filling_Page" & "Product_Cataloge_Page" class.
	
	WebDriver driver;    
	
	public Purchase_Page(WebDriver driver)     
	
	{
		super(driver);
		this.driver = driver;            
		PageFactory.initElements(driver, this);    
	}

	
	//PageFactory design pattern.

	@FindBy (xpath = "//*[contains(@class,\"input-field\")]/descendant::input[1]")
	WebElement country_input;  
	 
	@FindBy (css = "div[class=\"suggestions\"] ul li a")
	WebElement suggestion; 
	
	@FindBy (xpath = "//*[@for=\"checkbox2\"]")
	WebElement checkbox; 
	
	@FindBy (xpath = "//*[@type=\"submit\"]")
	WebElement submit; 
	
	
	
	By country_input_suggestions = By.cssSelector("div[class=\"suggestions\"] ul li a");   
	By successMessage_locator = By.xpath("//div/div[@class=\"alert alert-success alert-dismissible\"]");
	
  
	public void Adding_country_and_submit (String country)
	 
	 {
		country_input.sendKeys(country);
		wait_for_element_clickable(country_input_suggestions);
		suggestion.click();
		checkbox.click();
		submit.click();
      }
	
	public void Success_Message_Verification (String success_message_expected)
	 
	 {
		wait_for_text_presence(successMessage_locator,success_message_expected);
		System.out.println("***Printing Success Message***");
		String[] success = driver.findElement(successMessage_locator).getText().split("×");   //removing "×" from the string.
		System.out.println(success[1].strip());   //strip removes an additional line here.
		
     }

	}
