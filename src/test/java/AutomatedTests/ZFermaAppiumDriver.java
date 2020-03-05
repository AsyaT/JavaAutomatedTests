package AutomatedTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ZFermaAppiumDriver {

	private AppiumDriver<MobileElement> appiumDriver;
	
	private ZFermaAppiumDriver() {
    }

    private static ZFermaAppiumDriver instance = new ZFermaAppiumDriver();

    public static ZFermaAppiumDriver getInstance() {
        return instance;
    }
    
    private ThreadLocal<AppiumDriver> threadLocal = new ThreadLocal<AppiumDriver>() {

        protected AppiumDriver initialValue() 
        {
        	if(appiumDriver == null)
        	{
        	DesiredCapabilities capabilities = new DesiredCapabilities();
    		capabilities.setCapability("platformName", "Android");
    		capabilities.setCapability("deviceName", "Zebra MC33");
    		capabilities.setCapability("platformVersion", "7.1");
    		capabilities.setCapability("app", "C:\\AndroidProjects\\ZebraScanner\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
    		
    		try {
    			URL url = new URL("http://localhost:4723/wd/hub");
    			appiumDriver = new AppiumDriver<MobileElement>(url, capabilities);
    			appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    			//appiumDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    			
    			return appiumDriver;
    			 
	    		} catch (MalformedURLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
        	}
        	return appiumDriver;
        }
        

    };

    public AppiumDriver getAppiumDriver() {

        return threadLocal.get();
    }

    
    public void removeDriver() {
        appiumDriver = threadLocal.get();
        try {
            appiumDriver.quit();
        } finally {
            threadLocal.remove();
        }
        appiumDriver.close();
    }
    
}
