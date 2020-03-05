package AutomatedTests;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public  class CommonActions {

	static AppiumDriver<MobileElement> appiumDriver;
	static AndroidDriver androidDriver;
	
	public CommonActions()
	{
		appiumDriver = ZFermaAppiumDriver.getInstance().getAppiumDriver();
		androidDriver = ZFermaAndroidDriver.getInstance().getAndroidDriver();
	}
	
	public static String RemoveLineBreakes(String input)
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
	
	 public static Boolean IsElementExisis(String id)
	  {
		  try 
		  {
			  appiumDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			  
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
			  return true;
		  }
	  }
}
