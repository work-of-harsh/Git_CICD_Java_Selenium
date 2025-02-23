package Harsh_Project.Page_Objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Harsh_Project.AbstractComponents.AbstractComponent;

public class Product_Cataloge_Page extends AbstractComponent {
	
	//All comments to understand page object model is present on "Form_Filling_Page" & "Product_Cataloge_Page" class.
	
	WebDriver driver;    
	
	public Product_Cataloge_Page(WebDriver driver)     
	
	{
		super(driver);
		this.driver = driver;            
		PageFactory.initElements(driver, this);    
	}

	
	//PageFactory design pattern.
	@FindBy (xpath = "//app-card")
	List<WebElement> Cards;                 //List of WebElements will be used for chaining web elements in loop.
	
	@FindBy (css = "li[class*=\"item active\"] a")
	WebElement checkout_button_text;  
	
	@FindBy (css = "button[class*=\"btn-success\"]")
	WebElement final_checkout; 
	
	
	
	By mobilenames = By.xpath("div/div/h4[@class='card-title']/a");   //@FindBy is only used for webelement starting from driver.findElements.... 
	By adding_to_cart = By.xpath("div/div/button");
	String item = "item active";   //used variable in xpath : "+item+"
	By checkout_button = By.cssSelector("li[class*='"+item+"'] a");
  
	
	
	
	public void Adding_mobile_to_cart (String Mobile_name_entered)
	 
	 {
			for (int i = 0; i < Cards.size(); i++)
			{ 
				String MobileName = Cards.get(i).findElement(mobilenames).getText();   //from Cards going to the mobile name text.
			
				MobileName.strip();
				//if (MobileName == "Blackberry")   //didnt work so i used contains in next step.
				if (MobileName.contains(Mobile_name_entered))
				
				{
					
					Cards.get(i).findElement(adding_to_cart).click();    //from Cards going to the particular mobile click button.
					System.out.println(MobileName);
					System.out.println(MobileName + " Added to cart");
					
				}
	         }
      }

		public Purchase_Page checkout(String Checkout_item_number_text) 
	{
		System.out.println("***Printing Cart value***");
		System.out.println(checkout_button_text.getText());    //checkout button text is "Checkout ( 1 )  (current)".
		String CartValur = checkout_button_text.getText();
		Assert.assertEquals(true, CartValur.contains(Checkout_item_number_text));
		driver.findElement(checkout_button).click();     //clicking on checkout. Used variable into xpath here.
		final_checkout.click();
		Purchase_Page purchase_page = new Purchase_Page(driver);
		return purchase_page;
		
	}}
