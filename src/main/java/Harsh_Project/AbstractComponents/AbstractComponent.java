package Harsh_Project.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {   //AbstractComponent has all re-useable methods
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {     //driver coming from child classes using super(driver);
		this.driver=driver;
	}

	public void wait_for_element_clickable(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	
	public void wait_for_text_presence(By locator,String success_message_expected)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, success_message_expected));
	}
}
