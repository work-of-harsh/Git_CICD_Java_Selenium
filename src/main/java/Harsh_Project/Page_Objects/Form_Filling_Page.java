package Harsh_Project.Page_Objects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Harsh_Project.AbstractComponents.AbstractComponent;

public class Form_Filling_Page extends AbstractComponent{    //inherited parent class AbstractComponent
	
	WebDriver driver;    //local class variable.
	
	public Form_Filling_Page(WebDriver driver)     //constructor has the same name as class and is the first method to execute.
	
	{	super(driver);      //sending driver from child to parent.
		// connecting driver of main class with driver of class Form_Filling_Page.
		this.driver = driver;            //"this." refers to the local class variable.
		PageFactory.initElements(driver, this);    //driver here refers to the driver argument provided to the @FindBy annotation. "this" refers to the "WebDriver driver"
	}

	
	//PageFactory design pattern.
	@FindBy (xpath = "(//input)[1]")
	WebElement username;                  // will be constructed as "//WebElement username = driver.findElement(By.xpath("(//input)[1]"));".Page factory cant be used only for locators. (By......)
	
	@FindBy (xpath = "(//input)[2]")     
	WebElement email;  
	
	@FindBy (xpath = "(//input)[3]")
	WebElement password;  
	
	
	@FindBy (xpath = "(//input)[10]")
	WebElement display_name;  
	
	
	@FindBy (linkText = "Shop")
	WebElement shopbutton;  
	 public Product_Cataloge_Page form_fliing(String user_name, String Email, String Password)     //return type is "Product_Cataloge_Page" not void because we are returning "Product_Cataloge_Page" object.
	 
	 {
		 username.sendKeys(user_name);
		 email.sendKeys(Email);
		 password.sendKeys(Password);
		 System.out.println(display_name.getAttribute("value"));  //text not present so used :getAttribute("value")
		 shopbutton.click();
		 Product_Cataloge_Page product_catalogue = new Product_Cataloge_Page(driver);    //directly creating next page object here.
		 return product_catalogue;
	 }
 
	 
	 public void Goto()
	 
	 {
		 driver.get("https://rahulshettyacademy.com/angularpractice/");
		 
	 }

}
