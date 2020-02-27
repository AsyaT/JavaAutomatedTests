package AutomatedTests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.And;


public class SettingsTestScenario {
	
	static AppiumDriver<MobileElement> appiumDriver;

	@Given("^I run application")
	public void backgroidnGiven() throws Throwable 
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Zebra MC33");
		capabilities.setCapability("platformVersion", "7.1");
		capabilities.setCapability("app", "C:\\AndroidProjects\\ZebraScanner\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
		
		try {
			URL url = new URL("http://localhost:4723/wd/hub");
			appiumDriver = new AppiumDriver<MobileElement>(url, capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  @Given("^I press button '(.*?)'$")
  public void given(String btnName) throws Throwable {
	  
	  String buttonId = "";
	  
	  if(btnName.equals("Select operation"))
	  {
		  buttonId = "btnSelectOperation";
	  }
	  
	  MobileElement selectOperationButton = appiumDriver.findElement( By.id(buttonId));
      selectOperationButton.click();
  }

  @When("^you are in When annotation$")
  public void when() throws Throwable {
  }

  @Then("^I see error alert message '(.*?)'$")
  public void then(String message) throws Throwable {
	  MobileElement alertMessage = appiumDriver.findElement(By.id("android:id/alertTitle"));

      assertEquals(message, alertMessage.getText());

      MobileElement alertButton = appiumDriver.findElement(By.id("android:id/button1"));
      alertButton.click();
  }

  @And("^you are in And annotation$")
  public void and() throws Throwable {
  }

}
