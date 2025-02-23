package Harsh_Project.Selenium_Framework_Harsh;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertListContains;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShopPage {
	@Test
	public void PageShop()

	{
		WebDriverManager.chromedriver().setup();    //downloads lastes chromedriver version base on chrome browser version.
		WebDriver driver = new ChromeDriver();
	    driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		
		driver.findElement(By.xpath("(//input)[1]")).sendKeys("Harsh Vibhor Singh");
		driver.findElement(By.xpath("(//input)[2]")).sendKeys("thug@tcs.com");
		driver.findElement(By.xpath("(//input)[3]")).sendKeys("thug");  
		System.out.println(driver.findElement(By.xpath("(//input)[10]")).getAttribute("value"));   //text not present so used :getAttribute("value")
		driver.findElement(By.linkText("Shop")).click();
		List<WebElement> Cards = driver.findElements(By.xpath("//app-card"));    //will be used for chaining web elements.
		for (int i = 0; i < Cards.size(); i++)
		{ 
			String MobileName = Cards.get(i).findElement(By.xpath("div/div/h4[@class='card-title']/a")).getText();   //from Cards going to the mobile name text.
		
			MobileName.strip();
			//if (MobileName == "Blackberry")   //didnt work
			if (MobileName.contains("Blackberryy"))
			
			{
				
				Cards.get(i).findElement(By.xpath("div/div/button")).click();    //from Cards going to the particular mobile click button.
				System.out.println(MobileName);
				System.out.println(MobileName + " Added to cart");
				
			}
		
		
		}
		System.out.println("***Printing Cart value***");
		System.out.println(driver.findElement(By.cssSelector("li[class*=\"item active\"] a")).getText());    //text here is "Checkout ( 1 )  (current)".
		String CartValur = driver.findElement(By.cssSelector("li[class*=\"item active\"] a")).getText();
		Assert.assertEquals(true, CartValur.contains("Checkout ( 0 )"));
		String item = "item active";
		driver.findElement(By.cssSelector("li[class*='"+item+"'] a")).click();   //used variable in xpath : "+item+"
		
		driver.findElement(By.cssSelector("button[class*=\"btn-success\"]")).click();
		driver.findElement(By.xpath("//*[contains(@class,\"input-field\")]/descendant::input[1]")).sendKeys("India");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class=\"suggestions\"] ul li a")));
		driver.findElement(By.cssSelector("div[class=\"suggestions\"] ul li a")).click();
		driver.findElement(By.xpath("//*[@for=\"checkbox2\"]")).click();
		driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div/div[@class=\"alert alert-success alert-dismissible\"]"), "Success! Thank you! Your order will be delivered in next few weeks :-)."));
		System.out.println("***Printing Success Message***");
		String[] success = driver.findElement(By.xpath("//div/div[@class=\"alert alert-success alert-dismissible\"]")).getText().split("×");   //removing "×" from the string.
		System.out.println(success[1].strip());   //strip removes an additional line here.
		driver.close();
		
		
	}
	

}
