package AutomatedTests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\Features",glue = "AutomatedTests", tags = {"@Success"})
public class RunTests 
{
	
	

}
