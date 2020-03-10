package AutomatedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import cucumber.api.java.en.Then;

public class InventoryScenario {
	
	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	public InventoryScenario()
	{
		appiumDriver = ZFermaAppiumDriver.getInstance().getAppiumDriver();
		androidDriver = ZFermaAndroidDriver.getInstance().getAndroidDriver();
	}
	
  @Given("^Inventory activity is open$")
  public void givenInventoryActivityIsOpen() throws Throwable {
	  assertEquals(".InventoryActivity", androidDriver.currentActivity());
  }

  @Then("^I see Inventory activity$")
  public void thenIseeInventoryActivity() throws Throwable {
	  assertEquals(".InventoryActivity", androidDriver.currentActivity());
  }
  
}
