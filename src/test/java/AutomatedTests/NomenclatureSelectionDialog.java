package AutomatedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.openqa.selenium.By;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class NomenclatureSelectionDialog {
	
	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	public NomenclatureSelectionDialog()
	{
		appiumDriver = ZFermaAppiumDriver.getInstance().getAppiumDriver();
		androidDriver = ZFermaAndroidDriver.getInstance().getAndroidDriver();
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
			  String fixedLine = CommonActions.RemoveLineBreakes(lineToChoseName);
			  
			  assertEquals(fullName, fixedLine);
			  
			  lineToChooseElement.click();
		  }
		  
	  }  
}
