package AutomatedTests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.And;


public class SettingsTestScenario {
	
	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;

	public SettingsTestScenario() {
		appiumDriver = ZFermaAppiumDriver.getInstance().getAppiumDriver();
		androidDriver = ZFermaAndroidDriver.getInstance().getAndroidDriver();
	}
	
  
  @When("^I press button '(.*?)'$")
  public void whenIPressButton(String btnName) throws Throwable {
	  
	  PressButton(btnName);
  }

  protected void PressButton(String btnName)
  {
	  String buttonId = "";
	  
	  if(btnName.equalsIgnoreCase("Выберите операцию"))
	  {
		  buttonId = "btnSelectOperation";
	  }
	  else if(btnName.equalsIgnoreCase("Настройки"))
	  {
		  buttonId="btnSettings";
	  }
	  else if(btnName.equalsIgnoreCase("Сохранить"))
	  {
		  buttonId="btnSaveSettings";
	  }
	  else if(btnName.equalsIgnoreCase("Удалить строку"))
	  {
		  buttonId = "btnRemoveOne";
	  }
	  else if (btnName.equalsIgnoreCase("Удалить всё"))
	  {
		  buttonId = "btnRenoveAll";
	  }
	  else if(btnName.equalsIgnoreCase("Штрихкод"))
	  {
		  buttonId = "btnBarcodeInfo";
	  }
	  else if(btnName.equalsIgnoreCase("Назад к списку операций"))
	  {
		  buttonId="btnBack";
	  }
	  
	  MobileElement selectOperationButton = appiumDriver.findElement( By.id(buttonId));
      selectOperationButton.click();
  }
  
  @When("^I enter IP adress '(.*?)'$")
  public void whenIenterIPadress(String ipAdress) throws Throwable {
	  MobileElement urlTextEitor = appiumDriver.findElement(By.id("editTxtServer"));
	  urlTextEitor.clear();
	  urlTextEitor.sendKeys(ipAdress);

      appiumDriver.hideKeyboard();
  }
  
  @When("^I enter username '(.*?)'$")
  public void whenIenterusername(String username) throws Throwable {
	  MobileElement urlTextEitor = appiumDriver.findElement(By.id("editTxt1CUserName"));
	  urlTextEitor.clear();
      urlTextEitor.sendKeys(username);
  }
  
  @When("^I enter password '(.*?)'$")
  public void whenIenterpassword(String password) throws Throwable {
	  MobileElement urlTextEitor = appiumDriver.findElement(By.id("editTxt1CPassword"));
	  urlTextEitor.clear();
      urlTextEitor.sendKeys(password);
  }
  
  @When("^I go back$")
  public void whenIGoBack()
  {
	  MobileElement backButton = appiumDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Перейти вверх\"]"));
      backButton.click();
  }

  @Then("^I see error alert message '(.*?)'$")
  public void thenIseeErrorAlertMessage(String message) throws Throwable {
	  MobileElement alertMessage = appiumDriver.findElement(By.id("android:id/alertTitle"));

      assertEquals(message, alertMessage.getText());

      MobileElement alertButton = appiumDriver.findElement(By.id("android:id/button1"));
      alertButton.click();
  }

  @Then("^I see error screen '(.*?)'")
  public void thenIseeSeeErrorScreen(String message)
  {
	  MobileElement txtError = appiumDriver.findElement(By.id("txtNoConnectionInfo"));
	  assertEquals(message, txtError.getText());
	  
	  androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
  }
  
  @Then("^I see operation selection activity screen")
  public void thenIseeOperationSelectionActivity()
  {
	  int counter = 0;
	  while(androidDriver.currentActivity().contains("OperationSelection") == false) 
	  {
	      try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      counter++;
	      if ( counter >= 10 ) break;
	  }
	  
	  assertEquals(".OperationSelectionActivity", androidDriver.currentActivity());
  }

}
