package AutomatedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class RealizationScenario 
{
	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	public RealizationScenario()
	{
		appiumDriver = ZFermaAppiumDriver.getInstance().getAppiumDriver();
		androidDriver = ZFermaAndroidDriver.getInstance().getAndroidDriver();
	}
	
	 @Then("^I see Realization activity$")
	 public void thenIseeRealizationActivity() throws Throwable 
	 {
		  assertEquals(".RealizationActivity", androidDriver.currentActivity());
	 }
	 
	 
	 @Given("^I see offer to scan order '(.*?)'$")
	 public void givenISeeOfferToScanOrder(String message)
	 {
		 MobileElement txtScanOrderOffer = appiumDriver.findElement(By.id("txtOfferToScanOrder"));
		 assertEquals(message, txtScanOrderOffer.getText());
	 }
	 
	 @Then("^I see offer scan order fragment with message '(.*?)'$")
	 public void thenISeeOfferScanOrderFragmentWithMessage(String errrorMessage)
	 {
		 MobileElement txtErrorOfferScanOrder = appiumDriver.findElement(By.id("txtFragmentOrderScanError"));
		 assertEquals(errrorMessage, txtErrorOfferScanOrder.getText());
	 }
	 
	 @Then("^I see information with order name '(.*?)'$")
	 public void thenISeeInfrmantionWithOrderName(String orderName)
	 {
		 MobileElement txtOrderName= appiumDriver.findElement(By.id("txtOrderName"));
		 
		 assertEquals(orderName, txtOrderName.getText());
	 }
	 
	  @Then("^offer to scan order is closed$")
	  public void thenOfferToScanOrderIsClosed()
	  {
		  try
		  {
			  MobileElement inviteMessage = appiumDriver.findElement(By.id("txtFragmentOrderScanError"));
			  if(inviteMessage != null)
			  {
				  assertFalse(inviteMessage.isDisplayed());
			  }
		  }
		  catch (Exception e)
		  {
			  assertTrue(true);
		  }
	  }
	  
	  @Then("^screen with order table is closed$")
	  public void thenScreenWithOrderTableIsClosed()
	  {
		  try
		  {
			  MobileElement inviteMessage = appiumDriver.findElement(By.id("txtProgressOrderName"));
			  if(inviteMessage != null)
			  {
				  assertFalse(inviteMessage.isDisplayed());
			  }
		  }
		  catch (Exception e)
		  {
			  assertTrue(true);
		  }
		  
	  }
	  
	  @When("^I press on informaiton with order name$")
	  public void whenIPressOnInformationWithOrderName()
	  {
		  MobileElement fragmentOrderName = appiumDriver.findElement(By.id("frOrderInfo"));
		  fragmentOrderName.click();
	  }
	  
	  @Then("^I see table of products for order (.*?)$")
	  public void thenISeeTableWithOrdersProducts(String orderName)
	  {
		  MobileElement txtOrderName = appiumDriver.findElement(By.id("txtProgressOrderName"));
		  assertNotNull(txtOrderName);
		  assertEquals(orderName, txtOrderName.getText());
	  }
}
