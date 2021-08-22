package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass extends Parameter {
	
	
	
	protected static WebDriver driver;
	AppiumDriver<MobileElement> appiumdriver;
	AppiumDriverLocalService appiumService;
	String appiumServiceUrl;
	
	@BeforeSuite
	public void appiumServerKiller()
	{
	ProcessBuilder processBuilder = new ProcessBuilder();
	processBuilder.command("cmd.exe", "/C", "netstat -ano | find \":4723 \"");
	String line,s = "";  
    
    try {

        Process process = processBuilder.start();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        while ((line = reader.readLine()) != null) {
       	 
          
           	s=line.replaceAll("[A-Za-z]", "");
           	System.out.println(s);
               String[] a=s.split("\\s+");
                System.out.println(a[a.length-1]);
                String res=a[a.length-1];
	if(!(res=="0"))
	{
		processBuilder.command("cmd.exe", "/C", "taskkill /pid "+res+" /f");
		 process = processBuilder.start();
		System.out.println("taskkill /pid "+res+" /f");
	}
            
 }


} catch (IOException e) {
    e.printStackTrace();
}
	
	} 

	@BeforeTest
	public void setUP() throws MalformedURLException
	{
		 appiumService = AppiumDriverLocalService.buildDefaultService();
		 appiumService.start();
	     appiumServiceUrl = appiumService.getUrl().toString();
	        System.out.println("Appium Service Address : - "+ appiumServiceUrl);
		     
		        
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
		dc.setCapability(MobileCapabilityType.UDID, samsungWireless_uuid); //"RZ8R71QWTEX"
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
		dc.setCapability(MobileCapabilityType.NO_RESET, true);
		dc.setCapability("appPackage", emulatorirctc_appPackage);
		dc.setCapability("appActivity", emulatorirctc_appActivity);
		
		  
		driver = new AndroidDriver<>(new URL(appiumServiceUrl), dc);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  

		 
				
	}
	
	
	@AfterTest
	public void stopServer()
	{
		
	
		appiumService.stop();
		
	
}

}
