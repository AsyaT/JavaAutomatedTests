package AutomatedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ListViewInstructions {
	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	  List<MobileElement> fourTextViews = null;

	
	public ListViewInstructions()
	{
		appiumDriver = ZFermaAppiumDriver.getInstance().getAppiumDriver();
		androidDriver = ZFermaAndroidDriver.getInstance().getAndroidDriver();
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
	  
	  private MobileElement GetListViewString(Integer stringNumber)
	  {
		  MobileElement listView = appiumDriver.findElement(By.id("listViewProductContainer"));
		  	  
		  List<MobileElement> listViewScanStrings = listView.findElements(By.className("android.widget.LinearLayout"));
			 
		  // i=1 because listViewScanTable[0] = header
		  	  
		  for(int i = 1 ; i < listViewScanStrings.size() ; i ++ )
		  {
			  MobileElement stringTextViews = listViewScanStrings.get(i);
			  List<MobileElement> allTextViewsInString = stringTextViews.findElements(By.xpath("//android.widget.TextView"));
			  if(allTextViewsInString.get(0).getText().equalsIgnoreCase(stringNumber.toString()))
			  {
				  return stringTextViews;
			  }
		  }
		  
		  return null;
	  }
	  
	  public static void ScrollListView(String listViewId)
		 {
			 
			 MobileElement listView = appiumDriver.findElement(By.id(listViewId));
			  if (listView != null ) 
			  {
				  int middleX = listView.getLocation().getX() + listView.getSize().getWidth() / 2;
		          int upperY = listView.getLocation().getY();
		          int lowerY = upperY + listView.getSize().getHeight() - 50;     
				   
		        
				   new TouchAction(appiumDriver)
				   .press(PointOption.point(middleX, lowerY))
				   .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
				   .moveTo(PointOption.point(middleX, upperY))
				   .release()
				   .perform();
				   
			  }
		 }
	  
	  
	  @Then("^I see string in table with number '(.*?)'$")
	  public void thenISeeStringInTableWithNumber(Integer stringNumber)
	  {
		  MobileElement scannedStringLinearLayout = GetListViewString(stringNumber);
		  
		  if(scannedStringLinearLayout == null)
		  {
			  Integer scrollLimit = 30;
			  
			  while(scannedStringLinearLayout == null && scrollLimit > 0)
			  {  
				  ScrollListView("listViewProductContainer");
				  
				  scannedStringLinearLayout = GetListViewString(stringNumber);
				  scrollLimit--;
			  }
		  }
		  
		  fourTextViews = scannedStringLinearLayout.findElements(By.xpath("//android.widget.TextView"));
		  
		  assertEquals(stringNumber.toString(),fourTextViews.get(0).getText());
		  
	  }
	  
	  @Then("^I do not see string in table with number '(.*?)'$")
	  public void thenIDontSeeStringInTable(Integer stringNumber)
	  {
		  MobileElement scannedStringLinearLayout = GetListViewString(stringNumber);
		  assertNull(scannedStringLinearLayout);
	  }
	  
	  @And("^nomenclature name is '(.*?)'$")
	  public void andNomenclatureIs(String nomenclatureName)
	  {
		  String nomenclature = fourTextViews.get(1).getText();
		  
		  String calculatingElement = CommonActions.RemoveLineBreakes(nomenclature);
		  
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
	  
	  @When("^I press string number '(.*?)'$")
	  public void whenIPressStringNumber(Integer stringNumber)
	  {
		  MobileElement scannedStringLinearLayout =  GetListViewString(stringNumber);;
		  
		  assertNotNull(scannedStringLinearLayout);
		  
		  scannedStringLinearLayout.click();
		  
	  }
	  @Then("^the table is empty$")
	  public void thenTheTableIsEmpty()
	  {
		  List<MobileElement> listViewScanTable = appiumDriver.findElements(By.xpath("//android.widget.ListView//android.widget.LinearLayout"));
		  
		  Integer headerElement = 1;
		  
		  assertEquals(0, listViewScanTable.size() - headerElement);
	  }
}
