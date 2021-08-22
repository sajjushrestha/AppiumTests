package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utility.BaseClass;
import utility.ReusableMethods;

public class MobileApp extends BaseClass {

	@Test
	public void railApp() throws Exception {
		System.out.println("Rail App started");

		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='OK']"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Plan My Journey']"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cris.org.in.prs.ima:id/fromStn_code"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cris.org.in.prs.ima:id/tv_search_text"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cris.org.in.prs.ima:id/tv_search_text"))).sendKeys("rjy");
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cris.org.in.prs.ima:id/rv_station_list"))).click();
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cris.org.in.prs.ima:id/toStn_code"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cris.org.in.prs.ima:id/tv_search_text"))).sendKeys("hyd");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cris.org.in.prs.ima:id/rv_station_list"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cris.org.in.prs.ima:id/select_journey_date"))).click();
		
		dateSelect("//*[@content-desc='11 September 2021']");
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='OK']"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cris.org.in.prs.ima:id/tv_search"))).click();
		
		ReusableMethods.verticalScroll("//*[@text='(01020)']");
		
		String time=wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout//android.widget.TextView"))).getText();
		System.out.println(time);
		
		

	}
	
	public static void dateSelect(String locator)
	{
		int k=1,j=0;	
		
		while(j<k)
		{
			try {
			if(driver.findElement(By.xpath(locator)).isDisplayed())
				
			{
				j=k;
				driver.findElement(By.xpath(locator)).click();
				break;
			}
			}
			catch(Exception e)
			
			{
				driver.findElement(By.id("android:id/next")).click();
				    k++;
				
			}
		}
	}

}
