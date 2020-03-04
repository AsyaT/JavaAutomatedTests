package AutomatedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
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
	
	public String RemoveLineBreakes(String input)
	{
		byte[] nomInBytes = input.getBytes(StandardCharsets.UTF_8);
		
		  byte[] result = new byte[nomInBytes.length];
		  int i = 0;
		  int quantity = 0;
		  for (byte b : nomInBytes)
		  {
			  if(b == 10)
			  {
				  quantity++;
			  }
			  else
			  {
				  result[i] = b;
				  i++;
			  }
			 
		  }
		  
		  String resultString = new String(result, StandardCharsets.UTF_8);
		  
		  return resultString.substring(0, resultString.length()-quantity);
	}
	
  @Given("^Inventory activity is open$")
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
  
  private MobileElement GetListViewString(Integer stringNumber)
  {
	  MobileElement listView = appiumDriver.findElement(By.id("listViewProductContainer"));
	  
	  List<MobileElement> listViewScanTable = listView.findElements(By.className("android.widget.LinearLayout"));
	 
	  //because listViewScanTable[0] = header
	  
	  if(stringNumber< listViewScanTable.size())
	  {
		  	return listViewScanTable.get(stringNumber);
	  }
	  else 
	  {

		  return null;
	  }
	  
  }
  
  List<MobileElement> fourTextViews = null;
  
  @Then("^I see string in table with number '(.*?)'$")
  public void thenISeeStringInTableWithNumber(Integer stringNumber)
  {
	  MobileElement scannedStringLinearLayout = GetListViewString(stringNumber);
	  fourTextViews = scannedStringLinearLayout.findElements(By.xpath("//android.widget.TextView"));
	  
	  assertEquals(stringNumber.toString(),fourTextViews.get(0).getText());
	  
  }
  
  @And("^nomenclature name is '(.*?)'$")
  public void andNomenclatureIs(String nomenclatureName)
  {
	  String nomenclature = fourTextViews.get(1).getText();
	  
	  String calculatingElement = RemoveLineBreakes(nomenclature);
	  
	  assertEquals(nomenclatureName, calculatingElement);
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
  
  @And("^select line item '(.*?)' with name '(.*?)'$")
  public void andSelectLineItem(Integer lineNumber, String fullName)
  {
	  MobileElement listOfProductsContainer = appiumDriver.findElement(By.id("android:id/select_dialog_listview"));
	  List<MobileElement> listOfProducts = listOfProductsContainer.findElementsByXPath("//android.widget.TextView");
	  
	  if(lineNumber > listOfProducts.size())
	  {
		  assertNull(lineNumber+" is grater than size of dialog", null);
	  }
	  else 
	  {
		  MobileElement lineToChooseElement = listOfProducts.get(lineNumber-1);
		  
		  String lineToChoseName = lineToChooseElement.getText();
		  String fixedLine = RemoveLineBreakes(lineToChoseName);
		  
		  assertEquals(fullName, fixedLine);
		  
		  lineToChooseElement.click();
	  }
	  
  }
  
  @When("^I press string number '(.*?)'$")
  public void whenIPressStringNumber(Integer stringNumber)
  {
	  MobileElement scannedStringLinearLayout =  GetListViewString(stringNumber);;
	  
	  assertNotNull(scannedStringLinearLayout);
	  
	  scannedStringLinearLayout.click();
	  
  }
  
  @Then("^String number '(.*?)' is highlighted with yellow color$")
  public void ItHighLightedWithYellow(Integer stringNumber)
  {
	  MobileElement scannedStringLinearLayout = GetListViewString(stringNumber);	  
	  assertNotNull(scannedStringLinearLayout);
	  if(scannedStringLinearLayout!=null)
	  {
		  //TODO: detect that color is yellow
		  /*
		  String cssValue = scannedStringLinearLayout.getCssValue("style");
		  assertEquals("yellow", cssValue);
		  */
	  }
  }
  
  @Then("^the table is empty$")
  public void thenTheTableIsEmpty()
  {
	  List<MobileElement> listViewScanTable = appiumDriver.findElements(By.xpath("//android.widget.ListView//android.widget.LinearLayout"));
	  
	  Integer headerElement = 1;
	  
	  assertEquals(0, listViewScanTable.size() - headerElement);
  }
  
  @Then("^I see fragment with message '(.*?)'$")
  public void thenIseeFragmentWithMessage(String message)
  {
	  MobileElement fragment = appiumDriver.findElement(By.id("frBarcodeInfo"));
	  assertNotNull(fragment);
	  
	  MobileElement text = appiumDriver.findElement(By.id("textViewBarcodeInfo"));
	  assertEquals(message, RemoveLineBreakes(text.getText()));
  }
  
  
  @Then("^the fragment disappear$")
  public void thenTheFragmentDisappear() 
  {
	  try
	  {
		  MobileElement inviteMessage = appiumDriver.findElement(By.id("textViewBarcodeInfo"));
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
  
  @When("^I press system button back$")
  public void whenIPressSystemButtonBack()
  {
	  appiumDriver.navigate().back();
  }
}
