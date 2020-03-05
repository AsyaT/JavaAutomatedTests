package AutomatedTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ZFermaAndroidDriver {

	private AndroidDriver androidDriver;
	
	private ZFermaAndroidDriver() {
    }

    private static ZFermaAndroidDriver instance = new ZFermaAndroidDriver();

    public static ZFermaAndroidDriver getInstance() {
        return instance;
    }
    
    private ThreadLocal<AndroidDriver> threadLocal = new ThreadLocal<AndroidDriver>() {

        protected AndroidDriver initialValue() 
        {
        	if(androidDriver == null)
        	{
        	DesiredCapabilities capabilities = new DesiredCapabilities();
    		capabilities.setCapability("platformName", "Android");
    		capabilities.setCapability("deviceName", "Zebra MC33");
    		capabilities.setCapability("platformVersion", "7.1");
    		capabilities.setCapability("app", "C:\\AndroidProjects\\ZebraScanner\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
    		
    		try {
    			URL url = new URL("http://localhost:4723/wd/hub");
    			
    			androidDriver = new AndroidDriver(url, capabilities);
    			androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    			androidDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

    			return androidDriver;
    			 
	    		} catch (MalformedURLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
        	}
        	return androidDriver;
        }
        

    };

    public AndroidDriver getAndroidDriver() {

        return threadLocal.get();
    }

    
    public void removeDriver() {
    	androidDriver = threadLocal.get();
        try {
        	androidDriver.quit();
        } finally {
            threadLocal.remove();
        }
        androidDriver.close();
    }
    
}
