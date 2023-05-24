package iOS.tests.Pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class WebViews_Page {
	
	static IOSDriver driver;
	ExtentTest test; 
	
	public WebViews_Page(IOSDriver driver, ExtentTest test) 
	{
        this.driver = driver;
        this.test = test;
    }
	public void webViews() throws InterruptedException {
		List<WebElement> elements = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'UIKitCatalog'`]"));
	    if (!elements.isEmpty()) {
	        elements.get(0).click();
	    } else {
	        System.out.println("This test is currently running on IPAD");
	    }
		
		
		WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
		
		HashMap<String, Object> scroll = new HashMap<String, Object>();
		scroll.put("element", ((RemoteWebElement)ele).getId());
		
		scroll.put("direction", "down");
		driver.executeScript("mobile:scroll", scroll);
		
		driver.findElement(AppiumBy.accessibilityId("Web View")).click();
		test.log(LogStatus.INFO, "Web View clicked!");
		Thread.sleep(2000);
		
		String html = driver.findElement(AppiumBy.accessibilityId("This is HTML content inside a ")).getText();
		System.out.println("HTML content: " + html);
		test.log(LogStatus.INFO, "HTML Content: " + html);
	}

}

