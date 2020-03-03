package AutomatedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.openqa.selenium.By;

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
		 
		 this.OrderName = txtOrderName.getText();
		 
		 assertEquals(orderName, txtOrderName.getText());
	 }
	 
	 String OrderName = "";
	  
	  @Then("^offer to scan order is closed$")
	  public void thenOfferToScanOrderIsClosed()
	  {
		  MobileElement txtErrorOfferScanOrder = appiumDriver.findElement(By.id("txtFragmentOrderScanError"));
		  assertNull(txtErrorOfferScanOrder);
	  }
	  
	  @When("^I press on informaiton with order name$")
	  public void whenIPressOnInformationWithOrderName()
	  {
		  MobileElement fragmentOrderName = appiumDriver.findElement(By.id("frOrderInfo"));
		  fragmentOrderName.click();
	  }
	  
	  @Then("^I see table with ordered products$")
	  public void thenISeeTableWithOrdersProducts()
	  {
		  MobileElement txtOrderName = appiumDriver.findElement(By.id("txtProgressOrderName"));
		  assertNotNull(txtOrderName);
		  assertEquals(this.OrderName, txtOrderName.getText());
	  }
}
