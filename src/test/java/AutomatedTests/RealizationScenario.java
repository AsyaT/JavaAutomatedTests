package AutomatedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import cucumber.api.DataTable;
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
			  assertFalse(CommonActions.IsElementExisis("txtOfferToScanOrder"));
		  }
		  catch(Exception e)
		  {}
	  }
	 
	  
	  @Then("^screen with order table is closed$")
	  public void thenScreenWithOrderTableIsClosed()
	  {
		  try 
		  {
			  assertFalse(CommonActions.IsElementExisis("txtProgressOrderName"));
		  }
		  catch(Exception e)
		  {}
	  }
	  
	  @When("^I press on informaiton with order name$")
	  public void whenIPressOnInformationWithOrderName()
	  {
		  MobileElement fragmentOrderName = appiumDriver.findElement(By.id("frOrderInfo"));
		  fragmentOrderName.click();
	  }
	  
	  @Then("^I see table of products for order '(.*?)'$")
	  public void thenISeeTableWithOrdersProducts(String orderName)
	  {
		  MobileElement txtOrderName = appiumDriver.findElement(By.id("txtProgressOrderName"));
		  assertNotNull(txtOrderName);
		  assertEquals(orderName, txtOrderName.getText());
	  }
	  
	  @Then("^I see string table for product '(.*?)' follow$")
	  public void thenISeeStringTableForProduct(String productName, DataTable table)
	  {		  
		  MobileElement tableRow = appiumDriver.findElement(By.xpath("//android.widget.TableLayout//android.widget.TextView[contains(@text,'"+productName+"')]/parent::node()"));
		  List<MobileElement> linearLayouts = tableRow.findElements(By.xpath("//android.widget.LinearLayout"));
		  MobileElement linearLayoutKilos = linearLayouts.get(0);
		  MobileElement linearLayoutItems = linearLayouts.get(1);
		  
		  List<MobileElement> txtsLinearLayoutKilos = linearLayoutKilos.findElements(By.xpath("//android.widget.TextView"));
		  MobileElement txtOrderdKilos = txtsLinearLayoutKilos.get(0);
		  MobileElement txtDoneKilos = txtsLinearLayoutKilos.get(1);
		  MobileElement txtLeftKilos = txtsLinearLayoutKilos.get(2);
		  
		  List<MobileElement> txtsLinearLayoutItems = linearLayoutItems.findElements(By.xpath("//android.widget.TextView"));
		  MobileElement txtOrderdItems = txtsLinearLayoutItems.get(0);
		  MobileElement txtDoneItems = txtsLinearLayoutItems.get(1);
		  MobileElement txtLeftItems = txtsLinearLayoutItems.get(2);

		  
		  List<Map<String, String>> list = table.asMaps(String.class, String.class);
		  
		  assertEquals(list.get(0).get("orderedKg"), txtOrderdKilos.getText());
		  
		  String expectedDoneKg = list.get(0).get("doneKg");
		  String actualDoneKg = txtDoneKilos.getText();
		  
		  assertEquals(expectedDoneKg, actualDoneKg);
		  assertEquals(list.get(0).get("leftKg"), txtLeftKilos.getText());

		  assertEquals(list.get(0).get("orderedItm"), txtOrderdItems.getText());
		  assertEquals(list.get(0).get("doneItm"), txtDoneItems.getText());
		  assertEquals(list.get(0).get("leftItm"), txtLeftItems.getText());

	  }
}
