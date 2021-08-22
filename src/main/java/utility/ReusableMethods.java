package utility;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ReusableMethods extends BaseClass{
	
	public static void verticalDownScroll()
	{
		TouchAction  action =new TouchAction((PerformsTouchActions) driver);	
		Dimension size	=driver.manage().window().getSize();
		int width=size.width;
		int height=size.height;				
		int middleOfX=width/2;
		int startYCoordinate= (int)(height*.7);
		int endYCoordinate= (int)(height*.2);
						
		action.press(PointOption.point(middleOfX, startYCoordinate))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
		.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
	}
	
	public static void verticalScroll(String locator)
	{
		int k=1,j=0;	
		
		while(j<k)
		{
			try {
			if(driver.findElement(By.xpath(locator)).isDisplayed())
				
			{
				j=k;
				break;
			}
			}
			catch(Exception e)
			
			{
				    Dimension size = driver.manage().window().getSize();
				    Double hs=size.getHeight()* 0.5;
				    int hsi=hs.intValue();
				    System.out.println(hsi);
				   
				    Double he=size.getHeight()* 0.1;
				    int hei=he.intValue();
				    
				    System.out.println(hei);
				 
				    new TouchAction((PerformsTouchActions) driver)
			                .press(PointOption.point(0, hsi))
			                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
			                .moveTo(PointOption.point(0, hei))
			                .release().perform();
				    k++;
				
			}
		}
	}
	
	 public static void captureScreenShots(String name) throws IOException {
	        String folder_name="screenshot";
	        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        //Date format fot screenshot file name
	        DateFormat  df=new  SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
	        //create dir with given folder name
	        new File(folder_name).mkdir();
	        //Setting file name
	        String file_name=df.format(new Date())+".png";
	        //coppy screenshot file into screenshot folder.
	        FileUtils.copyFile(f, new File(folder_name + "/" +name+"_"+ file_name));
	    }
	 
	 public static void horizontalScroll()
		{
			 Dimension size = driver.manage().window().getSize();
			    Double hs=size.getHeight()* 0.5;
			    int hsi=hs.intValue();
			    System.out.println(hsi);
			   
			    Double he=size.getHeight()* 0.1;
			    int hei=he.intValue();
			    
			    System.out.println(hei);
			 
			    new TouchAction((PerformsTouchActions) driver)
		             .press(PointOption.point(hsi, 0))
		             .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
		             .moveTo(PointOption.point(hei, 0))
		             .release().perform();
			
			}
	 
	 
	 public static void swipe(int fromX,int fromY,int toX,int toY) {
		 
		 TouchAction action = new TouchAction((PerformsTouchActions) driver);
		 action.press(PointOption.point(fromX,fromY))
		 .waitAction(new WaitOptions().withDuration(Duration.ofMillis(3000))) //you can change wait durations as per your requirement
		 .moveTo(PointOption.point(toX, toY))
		 .release()
		 .perform();
		 }
	 
	 public static void swipeUp(WebElement element) {
		 TouchActions action = new TouchActions(driver);
	 action.scroll(element, 10, 100);
	 action.perform();}

}
