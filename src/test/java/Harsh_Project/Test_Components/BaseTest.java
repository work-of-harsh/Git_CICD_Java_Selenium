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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
		
		
		
		//properties class  properties file shoilld have .properties extension.
		Properties prop =new Properties();
		FileInputStream fil = new FileInputStream("/Users/Lenovo/eclipse-workspace/Selenium_Framework_Harsh/src/main/java/Harsh_Project/resources/GlobalData.properties");
		prop.load(fil);
		String browsername = prop.getProperty("browser");
		
		if (browsername.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();    //downloads latest chromedriver version base on chrome browser version.
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true); 
		
		driver = new ChromeDriver(options);        }          //driver here connected with global variable WebDriver driver
		
		else if (browsername.equalsIgnoreCase("firefox")) {
		   // WebDriverManager.firefoxdriver().setup(); // Sets up the Firefox driver
			System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe"); 
		    FirefoxOptions options = new FirefoxOptions();
		    options.setAcceptInsecureCerts(true); // Accepts insecure certificates

		    driver = new FirefoxDriver(options); // Launches Firefox with the configured options
		    System.out.println("Launching Firefox browser with insecure certs accepted...");
		}

		
		else 
		{  //another browser initialization  .  
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
			
			//read json to string using FileUtils.readFileToString
			String Json_content = FileUtils.readFileToString(new File("C:\\Users\\Lenovo\\eclipse-workspace\\Selenium_Framework_Harsh\\src\\test\\java\\Harsh_Project\\data\\Input_data_file.json"),StandardCharsets.UTF_8);
		    //String to Hashmap using Jakson Databind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data_of_json = mapper.readValue(Json_content, new TypeReference<List<HashMap<String, String>>>() {
		      });
			  return data_of_json;  //returning a list of hash maps.
		
		}

	}
	
}
