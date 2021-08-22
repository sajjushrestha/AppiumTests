package demo;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import utility.BaseClass;
import utility.ReusableMethods;

public class Flipkart extends BaseClass {
	
	
	
	@Test
	public void flipApp() throws Exception
	{
		System.out.println("Flipkart App started");
		
	
		WebDriverWait wait=new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/logo_icon")));
		
		ReusableMethods.verticalDownScroll();
		
		ReusableMethods.captureScreenShots("Search");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Search for Products, Brands and More']"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/search_autoCompleteTextView"))).sendKeys("Mobiles");
		
		ReusableMethods.verticalDownScroll();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/back_icon"))).click();
		
		/*
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.
		 * AndroidUIAutomator( "new UiScrollable(new UiSelector().scrollable(true))" +
		 * ".scrollIntoView(new UiSelector().resourceIdMatches(\".*part_id.*\"))")));
		 */
		ReusableMethods.swipeUp(driver.findElement(By.xpath(".//*[@content-desc='Drawer']")));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("mobile: scroll", "direction: up");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@content-desc='Drawer']"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@text='Home']"))).click();
		
		ReusableMethods.captureScreenShots("Home");
		
		
		
	}
	
	

}
