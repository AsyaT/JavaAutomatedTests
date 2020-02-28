package AutomatedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.openqa.selenium.By;

import cucumber.api.java.en.And;
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

  @Then("^I see Inventory activity$")
  public void thenIseeInventoryActivity() throws Throwable {
	  assertEquals(".InventoryActivity", androidDriver.currentActivity());
  }

  @Then("^I see alert title '(.*?)' and message '(.*?)'$")
  public void thenIseeAlertMessage(String title, String message)
  {
	  MobileElement alertTitle = appiumDriver.findElement(By.id("android:id/alertTitle"));
      assertEquals(title, alertTitle.getText());
      
      MobileElement alertMessage = appiumDriver.findElement(By.id("android:id/message"));
      assertEquals(message, alertMessage.getText());

      MobileElement alertButton = appiumDriver.findElement(By.id("android:id/button1"));
      alertButton.click();
  }
  
  List<MobileElement> fourTextViews = null;
  
  @Then("^I see string in table with number '(.*?)'$")
  public void thenISeeStringInTableWithNumber(Integer stringNumber)
  {
	  List<MobileElement> listViewScanTable = appiumDriver.findElements(By.xpath("//android.widget.ListView//android.widget.LinearLayout"));
	  // listViewScanTable[0] = header
	  
	  MobileElement scannedStringLinearLayout = listViewScanTable.get(stringNumber);
	  fourTextViews = scannedStringLinearLayout.findElements(By.xpath("//android.widget.TextView"));
	  
	  assertEquals(stringNumber.toString(),fourTextViews.get(0).getText());
	  
  }
  
  @And("^nomenclature name is '(.*?)'$")
  public void andNomenclatureIs(String nomenclatureName)
  {
	  String nomenclature = fourTextViews.get(1).getText();
	  
	  byte[] nomInBytes = nomenclature.getBytes(StandardCharsets.UTF_8);
	  byte[] result = new byte[nomInBytes.length-1];
	  int i = 0;
	  for (byte b : nomInBytes)
	  {
		  if(b == 10)
		  {
		  }
		  else
		  {
			  result[i] = b;
			  i++;
		  }
		 
	  }
	  
	  assertEquals(nomenclatureName, new String(result, StandardCharsets.UTF_8));
  }
  
  @And("^weight is '(.*?)'$")
  public void andWeightIs(String weight)
  {
	  assertEquals(weight, fourTextViews.get(2).getText());
  }
  
  @And("^quantity is '(.*?)'$")
  public void andQuantityIs(String quantity)
  {
	  assertEquals(quantity,fourTextViews.get(3).getText());
  }
  
  @Then("^I see dialog to chose correct nomenclature$")
  public void thenISeeDialogToChoseNomenclature()
  {
	  MobileElement dialog = appiumDriver.findElement(By.id("android:id/alertTitle"));
	  assertEquals("Выберете номенклатуру", dialog.getText());
  }
  
  @And("^select line item '(.*?)'$")
  public void andSelectLineItem(Integer lineNumber)
  {
	  MobileElement listOfProductsContainer = appiumDriver.findElement(By.id("android:id/select_dialog_listview"));
	  List<MobileElement> listOfProducts = listOfProductsContainer.findElementsByXPath("//android.widget.TextView");
	  
	  if(lineNumber > listOfProducts.size())
	  {
		  assertNull(lineNumber+" is grater than size of dialog", null);
	  }
	  else 
	  {
		  listOfProducts.get(lineNumber).click();
	  }
	  
  }
}
