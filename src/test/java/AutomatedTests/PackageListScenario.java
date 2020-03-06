package AutomatedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.openqa.selenium.By;

import cucumber.api.*;
import cucumber.api.java.en.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class PackageListScenario {
	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	public PackageListScenario()
	{
		appiumDriver = ZFermaAppiumDriver.getInstance().getAppiumDriver();
		androidDriver = ZFermaAndroidDriver.getInstance().getAndroidDriver();
	}
	
	 @Then("^I see Package list activity$")
	 public void thenIseePackageListActivity() throws Throwable 
	 {
		  assertEquals(".PackageListActivity", androidDriver.currentActivity());
	 }
	 
	 @Given("I see offer to select action in dialog '(.*?)'$")
	 public void givenISeeOfferToSelectAction(String title, DataTable actionsDataTable)
	 {
		 MobileElement dialog = appiumDriver.findElement(By.id("android:id/alertTitle"));
		 assertEquals(title, dialog.getText());
		 
		 MobileElement listOfActionsContainer = appiumDriver.findElement(By.id("android:id/select_dialog_listview"));
		 List<MobileElement> listOfActions = listOfActionsContainer.findElementsByXPath("//android.widget.TextView");

		 List<String> givenActions = actionsDataTable.asList(String.class);
		 
		 assertEquals(givenActions.get(0), listOfActions.get(0).getText());
		 assertEquals(givenActions.get(1), listOfActions.get(1).getText());

	 }
	 
	 @When("^I select in action dialog item '(.*?)'$")
	 public void whenISelectInActionDialogItem(String itemName)
	 {
		 MobileElement listOfActionsContainer = appiumDriver.findElement(By.id("android:id/select_dialog_listview"));

		 MobileElement action = listOfActionsContainer.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+itemName+"')]"));
	 
		 assertNotNull(action);
		 
		 action.click();
	 }
}
