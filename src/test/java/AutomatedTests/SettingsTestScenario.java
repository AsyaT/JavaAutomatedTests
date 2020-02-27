package AutomatedTests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
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
	static AndroidDriver androidDriver;

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
			androidDriver = new AndroidDriver(url, capabilities);
			 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  
  @When("^I press button '(.*?)'$")
  public void whenIPressButton(String btnName) throws Throwable {
	  
	  PressButton(btnName);
  }

  protected void PressButton(String btnName)
  {
	  String buttonId = "";
	  
	  if(btnName.equalsIgnoreCase("Выберите операцию"))
	  {
		  buttonId = "btnSelectOperation";
	  }
	  else if(btnName.equalsIgnoreCase("Настройки"))
	  {
		  buttonId="btnSettings";
	  }
	  else if(btnName.equalsIgnoreCase("Сохранить"))
	  {
		  buttonId="btnSaveSettings";
	  }
	  
	  MobileElement selectOperationButton = appiumDriver.findElement( By.id(buttonId));
      selectOperationButton.click();
  }
  
  @When("^I enter IP adress '(.*?)'$")
  public void whenIenterIPadress(String ipAdress) throws Throwable {
	  MobileElement urlTextEitor = appiumDriver.findElement(By.id("editTxtServer"));
      urlTextEitor.setValue(ipAdress);

      appiumDriver.hideKeyboard();
  }
  
  @When("^I enter username '(.*?)'$")
  public void whenIenterusername(String username) throws Throwable {
	  MobileElement urlTextEitor = appiumDriver.findElement(By.id("editTxt1CUserName"));
      urlTextEitor.setValue(username);
  }
  
  @When("^I enter password '(.*?)'$")
  public void whenIenterpassword(String password) throws Throwable {
	  MobileElement urlTextEitor = appiumDriver.findElement(By.id("editTxt1CPassword"));
      urlTextEitor.setValue(password);
  }
  
  @When("^I go back$")
  public void whenIGoBack()
  {
	  MobileElement backButton = appiumDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Перейти вверх\"]"));
      backButton.click();
  }

  @Then("^I see error alert message '(.*?)'$")
  public void thenIseeErrorAlertMessage(String message) throws Throwable {
	  MobileElement alertMessage = appiumDriver.findElement(By.id("android:id/alertTitle"));

      assertEquals(message, alertMessage.getText());

      MobileElement alertButton = appiumDriver.findElement(By.id("android:id/button1"));
      alertButton.click();
  }

  @Then("^I see operation selection activity screen")
  public void thenIseeOperationSelectionActivity()
  {
	  assertEquals(".OperationSelectionActivity", androidDriver.currentActivity());
  }
  

}
