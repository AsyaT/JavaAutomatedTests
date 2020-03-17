package AutomatedTests;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CommonInstructions {
	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	public CommonInstructions()
	{
		appiumDriver = ZFermaAppiumDriver.getInstance().getAppiumDriver();
		androidDriver = ZFermaAndroidDriver.getInstance().getAndroidDriver();
	}
	
	@When("^I press button '(.*?)'$")
	public void whenIPressButton(String btnName) throws Throwable 
	{
		String buttonId = GetButtonId(btnName);
		  if(IsElementExisis(buttonId)) 
		  {
			  PressButton(buttonId);
		  }
		  else
		  {
			  ScrollPage();
			  PressButton(buttonId);
		  }
	  }
	
	//TODO: possibly need to move method "IsElementExisis" to FragmentInstructions. Might work incorrectly with different class
	
	 public static Boolean IsElementExisis(String id)
	  {
		  try 
		  {
			  appiumDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			  
			  if(appiumDriver.findElements(By.id(id)).size() == 0)
			  {
				  appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				  return false;
			  }
			  else
			  {
				  appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				  return true;
			  }
		  }
		  catch(Exception e)
		  {
			 e.printStackTrace();
			 return null;
		  }
		
	  }
		 
		 
	protected void ScrollPage()
	{
		Dimension size = appiumDriver.manage().window().getSize();
	    int startX = size.width / 2;
	    int startY = (int) (size.height * .8);
	    int endY = (int) (size.height * .2);
	    
	    new TouchAction(appiumDriver)
		   .press(PointOption.point(startX, startY))
		   .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
		   .moveTo(PointOption.point(startX, endY))
		   .release()
		   .perform();
	}

	  protected String GetButtonId(String btnName)
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
		  
		  return buttonId;
	  }
	  
	  protected void PressButton(String buttonId)
	  {
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
