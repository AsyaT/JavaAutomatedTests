package AutomatedTests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class CommonInstructions {
	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	public CommonInstructions()
	{
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
	  
	  @Then("^I see error alert message '(.*?)'$")
	  public void thenIseeErrorAlertMessage(String message) throws Throwable {
		  MobileElement alertMessage = appiumDriver.findElement(By.id("android:id/alertTitle"));

	      assertEquals(message, alertMessage.getText());

	      MobileElement alertButton = appiumDriver.findElement(By.id("android:id/button1"));
	      alertButton.click();
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
	  
	  
	  @When("^I press system button back$")
	  public void whenIPressSystemButtonBack()
	  {
		  KeyEvent backEvent = new KeyEvent(AndroidKey.BACK);
		  androidDriver.pressKey(backEvent);
	  }
}
