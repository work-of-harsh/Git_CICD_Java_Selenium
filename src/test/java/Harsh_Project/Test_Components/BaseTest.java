package Harsh_Project.Test_Components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Harsh_Project.Page_Objects.Form_Filling_Page;
import Harsh_Project.data.Data_Reader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Form_Filling_Page form_filling_page;                   //putting public before variable so child classes can use it outside method.
	public WebDriver initializeDriver() throws IOException {
		
		
		
		//properties class
		Properties prop =new Properties();
		FileInputStream fil = new FileInputStream("/Users/Lenovo/eclipse-workspace/Selenium_Framework_Harsh/src/main/java/Harsh_Project/resources/GlobalData.properties");
		prop.load(fil);
		String browsername = prop.getProperty("browser");
		
		if (browsername.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();    //downloads latest chromedriver version base on chrome browser version.
		driver = new ChromeDriver();        }          //driver here connected with global variable WebDriver driver;
		
		else 
		{  //firefox browser initialization  .  
			}
		
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
		
		
	}
	@BeforeMethod
	public Form_Filling_Page launching_form_filling_page() throws IOException     //providing driver to the first page of the page object file.
	{
		driver = initializeDriver();
		form_filling_page   = new Form_Filling_Page(driver);
		form_filling_page.Goto();              //opens the ECommerce page.
		return form_filling_page;
	}
	
	@AfterMethod
	public void tearDowwn() 
	{
		driver.close();	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException 
	{
		File scn_shot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);      //we casted driver object into TakesScreenshot method, TakesScreenshot method becomes equal to driver object.
        //scn_shot object has screenshot file.
        FileUtils.copyFile(scn_shot, new File(System.getProperty("user.dir") +"//Reports//" + testCaseName +".png"));
        return System.getProperty("user.dir") +"//Reports//" + testCaseName +".png";
	}
	
	
	//parameterization using json file.
	public class Data_Reader {
		
		public  List<HashMap<String, String>> getJsonDataToMap() throws IOException {
			
			//read json to string
			String Json_content = FileUtils.readFileToString(new File("C:\\Users\\Lenovo\\eclipse-workspace\\Selenium_Framework_Harsh\\src\\test\\java\\Harsh_Project\\data\\Input_data_file.json"),StandardCharsets.UTF_8);
		    //String to Hashmap using Jakson Databind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data_of_json = mapper.readValue(Json_content, new TypeReference<List<HashMap<String, String>>>() {
		      });
			  return data_of_json;
		
		}

	}
	
}
