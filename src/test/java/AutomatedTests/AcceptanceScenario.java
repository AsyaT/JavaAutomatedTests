package AutomatedTests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AcceptanceScenario {
	
	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	public AcceptanceScenario()
	{
		appiumDriver = ZFermaAppiumDriver.getInstance().getAppiumDriver();
		androidDriver = ZFermaAndroidDriver.getInstance().getAndroidDriver();
	}

	@Then("^I see AccountingAreaSelection activity$")
	 public void thenISeeAccountingAreaSelectionActivity() throws Throwable 
	 {
		  assertEquals(".AccountAreaSelectionActivity", androidDriver.currentActivity());
	 }
	
	@Then("^I see Acceptance activity$")
	 public void thenISeeRealizationActivity() throws Throwable 
	 {
		  assertEquals(".RealizationActivity", androidDriver.currentActivity());
	 }
	
	@When("I select '(.*?)' in list of accounting areas$")
	  public void whenIselectInListOfOperationTypes(String tapListName) throws Throwable 
	  {
		  List<MobileElement> listView = appiumDriver.findElements(By.xpath("//android.widget.ListView//android.widget.LinearLayout"));

	      for (MobileElement element : listView)
	      {
	          MobileElement textElement = element.findElement(By.xpath("//android.widget.TextView"));
	          if (textElement.getText().equalsIgnoreCase(tapListName))
	          {
	              element.click();
	          }
	      }

	      MobileElement okButton = appiumDriver.findElement(By.id("OKButtonAA"));
	      okButton.click();

	  }
}
