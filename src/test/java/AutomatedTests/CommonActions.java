package AutomatedTests;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public  class CommonActions {

	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	public CommonActions()
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
	  
	public static String RemoveLineBreakes(String input)
	{
		byte[] nomInBytes = input.getBytes(StandardCharsets.UTF_8);
		
		  byte[] result = new byte[nomInBytes.length];
		  int i = 0;
		  int quantity = 0;
		  for (byte b : nomInBytes)
		  {
			  if (b == 10)
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
	
	public static String RemoveSpaces(String input)
	{
		byte[] nomInBytes = input.getBytes(StandardCharsets.UTF_8);
		
		  byte[] result = new byte[nomInBytes.length];
		  int i = 0;
		  int quantity = 0;
		  for (byte b : nomInBytes)
		  {
			  if ((b == -30) || (b == -128) || (b == -83))
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
	
	 public static Boolean IsElementExisis(String id) throws Exception
	  {
		  try 
		  {
			  appiumDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			  
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
			  throw new Exception();
		  }
	  }
	 
	 
}
