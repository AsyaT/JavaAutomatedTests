package AutomatedTests;

import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public  class CommonActions {
	  
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
	
	
	 
}
