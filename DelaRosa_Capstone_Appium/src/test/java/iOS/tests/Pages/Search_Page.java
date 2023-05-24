package iOS.tests.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class Search_Page {
	
	static IOSDriver driver;
	ExtentTest test;
	
	public Search_Page(IOSDriver driver, ExtentTest test) 
	{
        this.driver = driver;
        this.test = test;
    }
	public void search() {
		List<WebElement> elements = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'UIKitCatalog'`]"));
	    if (!elements.isEmpty()) {
	        elements.get(0).click();
	    } else {
	        System.out.println("This test is currently running on IPAD");
	    }
		
	    
	    
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Search'`]")).click();
		
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Default'`]")).click();
		
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Scope Two'`]")).click();
		
		test.log(LogStatus.PASS, "Successfully changed from Scope One to Scope Two");
		
		List<WebElement> elements1 = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Search'`]"));
	    if (!elements1.isEmpty()) {
	        elements1.get(0).click();
	    } else {
	        System.out.println("This test is currently running on IPAD");
	    }
		
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Custom'`]")).click();

		driver.findElement(By.xpath("//XCUIElementTypeSearchField")).click();
		
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeKey[`label == 'R'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeKey[`label == 'o'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeKey[`label == 's'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeKey[`label == 't'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeKey[`label == 'e'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeKey[`label == 'l'`]")).click();
		
		List<WebElement> elements2 = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Search'`]"));
	    if (!elements2.isEmpty()) {
	        elements2.get(0).click();
	    } else {
	        System.out.println("This test is currently running on IPAD");
	    }
	}
}
