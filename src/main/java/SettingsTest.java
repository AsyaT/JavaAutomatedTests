import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SettingsTest {

	WebDriver webDriver;
	static AppiumDriver<MobileElement> appiumDriver;
	AndroidDriver androidDriver;
	
	public static void main(String[] args) {
		try {
			Init();
		}
		catch (MalformedURLException e) {
			// TODO: handle exception
		}

	}
	
	public static void Init() throws MalformedURLException
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Zebra MC33");
		capabilities.setCapability("platformVersion", "7.1");
		capabilities.setCapability("app", "C:\\AndroidProjects\\ZebraScanner\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
		
		URL url = new URL("http://localhost:4723/wd/hub");
		
		appiumDriver = new AppiumDriver<MobileElement>(url, capabilities);
       
	}

}
