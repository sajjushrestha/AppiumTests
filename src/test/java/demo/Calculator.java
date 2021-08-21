package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Calculator {
	
	WebDriver driver;
	AppiumDriver<MobileElement> appiumDriver;
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
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung");
		dc.setCapability(MobileCapabilityType.UDID, "RZ8R71QWTEX"); //"RZ8R71QWTEX"
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
		dc.setCapability(MobileCapabilityType.NO_RESET, true);
		dc.setCapability("appPackage", "com.sec.android.app.popupcalculator");
		dc.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
		
		  
		driver = new AndroidDriver<>(new URL(appiumServiceUrl), dc);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  

		 
				
	}
	
	@Test
	public void calciApp()
	{
		System.out.println("App started");
		
		System.out.println("Calculate sum of two numbers");
		// Locate elements to enter data and click +/= buttons
		driver.findElement(By.xpath("//*[@text='1']")).click();
		driver.findElement(By.xpath("//*[@text='+']")).click();
		driver.findElement(By.xpath("//*[@text='2']")).click();
		driver.findElement(By.xpath("//*[@text='=']")).click();
	}
	
	@AfterTest
	public void stopServer()
	{
		
	
		appiumService.stop();
		
	
}

}
