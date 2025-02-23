package Harsh_Project.Selenium_Framework_Harsh;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertListContains;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Harsh_Project.Page_Objects.Form_Filling_Page;
import Harsh_Project.Page_Objects.Product_Cataloge_Page;
import Harsh_Project.Page_Objects.Purchase_Page;
import Harsh_Project.Test_Components.BaseTest;
import Harsh_Project.data.Data_Reader;

public class ShopPage_with_framework extends BaseTest{
	@Test(dataProvider="getData")    //dataProvider="getData" for parameterization.
	public void Mobile_Shop1(HashMap<String,String> input) throws IOException   //input catches data from getData method using data provider.

	{
		
		Product_Cataloge_Page product_catalogue= form_filling_page.form_fliing(input.get("name"),input.get("email"), input.get("password"));
		product_catalogue.Adding_mobile_to_cart(input.get("mobilename"));
		Purchase_Page purchase_page = product_catalogue.checkout("Checkout ( 1 )");
		purchase_page.Adding_country_and_submit(input.get("country"));
		purchase_page.Success_Message_Verification(input.get("success_message"));

		
		
	}
	@Test
	public void Mobile_Shop2() throws IOException

	{
		
		Product_Cataloge_Page product_catalogue= form_filling_page.form_fliing("Harsh Vibhor Singh", "thug@tcs.com", "thug");
		product_catalogue.Adding_mobile_to_cart("Nokia Edge");
		Purchase_Page purchase_page = product_catalogue.checkout("Checkout ( 1 )");
		purchase_page.Adding_country_and_submit("India");
		purchase_page.Success_Message_Verification("Success! Thank you! Your order will be delivered in next few weeks :-).");

		
		
	}
	
	@DataProvider    //annotation used for parameterization
	public Object[][] getData() throws IOException {  //we use 2D array type as object, as parameter can be integer as well as string.
		//return new Object[][] { {"Harsh Vibhor Singh","thug@tcs.com","thug","Nokia Edge","India","Success! Thank you! Your order will be delivered in next few weeks :-)."},{"Harsh Vibhor Singh","thug@tcs.com","thug","Blackberry","China","Success! Thank you! Your order will be delivered in next few weeks :-)."} };
		Data_Reader obj = new Data_Reader();
		List<HashMap<String,String>> data = obj.getJsonDataToMap();
		return new Object[][]	{{data.get(0)},{data.get(1)}};
	}
	
}
