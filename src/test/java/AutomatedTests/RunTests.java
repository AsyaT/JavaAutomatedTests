package AutomatedTests;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\Features",glue = "AutomatedTests")
public class RunTests 
{
	
	

}
