package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="Cucumber.stepDefinations",monochrome=true,plugin= {"html:Cucumber_Report/Cucumber_report.html"},tags="@Purchase_in_China")
public class TestNg_Cucumber_TestRunner extends AbstractTestNGCucumberTests {
//features="src/test/java/Cucumber"  --->feature file location
//glue="Cucumber.stepDefinations"   ---> stepDefinations file locations
//monochrome=true ---> decodes in readable format.
	//plugin= {"html:Cucumber_Report/Cucumber_report.html"} ---> html doc to be created location provided in key:value pair.
	//extends AbstractTestNGCucumberTests ---> gets TestNG parent classes to read TestNG codes present in step definitions.
	//tags="@Purchase_in_China" ---> runs only tag "@Purchase_in_China"  from features files.
	//we can use Junit hybrid framework with cucumber too,just like TestNG.
}
