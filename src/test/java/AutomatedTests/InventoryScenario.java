package AutomatedTests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import cucumber.api.java.en.Then;

public class InventoryScenario {
	
	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	public InventoryScenario()
	{
		appiumDriver = ZFermaAppiumDriver.getInstance().getAppiumDriver();
		androidDriver = ZFermaAndroidDriver.getInstance().getAndroidDriver();
	}
	
  @Given("^Inventory acativity is open$")
  public void givenInventoryActivityIsOpen() throws Throwable {
	  assertEquals(".InventoryActivity", androidDriver.currentActivity());
  }
  
  @When("I scan '(.*?)' barcode '(.*?)'$")
  public void whenIScanBarcode(String barcodeType, String barcode)
  {
	  MobileElement txtBarcodeInput = appiumDriver.findElement(By.id("txtEditBarcodeToScan"));
	  txtBarcodeInput.clear();
      txtBarcodeInput.setValue(barcode+","+barcodeType);

      MobileElement btnDoScan = appiumDriver.findElement(By.id("btnDoScan"));
      btnDoScan.click();
  }

  @When("I select '(.*?)' in list of operation types$")
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

      MobileElement okButton = appiumDriver.findElement(By.id("OKButton"));
      okButton.click();

  }

  @Then("^I see Inventory activityn$")
  public void thenIseeInventoryActivity() throws Throwable {
	  assertEquals(".InventoryActivity", androidDriver.currentActivity());
  }

}
