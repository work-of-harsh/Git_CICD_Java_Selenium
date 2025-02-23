package Cucumber.stepDefinations;

import java.io.IOException;

import Harsh_Project.Page_Objects.Form_Filling_Page;
import Harsh_Project.Page_Objects.Product_Cataloge_Page;
import Harsh_Project.Page_Objects.Purchase_Page;
import Harsh_Project.Test_Components.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination_Implement extends BaseTest  {
	
  public 	Form_Filling_Page Ecommerce_page ;             // declaring Ecommerce_page globally so it can be used by other methods. //launching_form_filling_page() returns data type Form_Filling_Page
  public 	Product_Cataloge_Page mobile_catagalogue ;  
  public 	Purchase_Page selecting_country ; 
  
  
	@Given ("I landed on the Ecommerce Page")
	public void landed_on_the_Ecommerce_Page() throws IOException
	
	{
		Ecommerce_page = launching_form_filling_page();     
	}
	
	@Given("^Logged in with username (.+) , email (.+)  and password (.+)$")   //using regex- regular expression.
	
	public void Logged_in_with_username(String name, String email, String password) 
	{
		mobile_catagalogue = Ecommerce_page.form_fliing(name, email, password);
	}
	
	@When ("^I add mobile (.+) to the cart$")

	public void Adding_Mobile_name(String mobilename ) 
	{
		mobile_catagalogue.Adding_mobile_to_cart(mobilename);
		selecting_country = mobile_catagalogue.checkout("Checkout ( 1 )");
	} 

	@And("^I add country (.+)$")
	public void Adding_Country(String country ) 
	{
		selecting_country.Adding_country_and_submit(country);
	}
	
	@Then ("I verify the success message {string}")            //we are directly using {string} as it is coming directly from step definition. regex is for table dynamic data.
	public void success_message(String success_message )
	{
		selecting_country.Success_Message_Verification(success_message);
		driver.close();
	}

}