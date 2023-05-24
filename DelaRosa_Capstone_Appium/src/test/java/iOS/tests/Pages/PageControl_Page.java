package iOS.tests.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class PageControl_Page {

	static IOSDriver driver;
	ExtentTest test;
	
	public PageControl_Page(IOSDriver driver, ExtentTest test) 
	{
        this.driver = driver;
        this.test = test;
    }
	
	public void pageControl() {

		List<WebElement> elements = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'UIKitCatalog'`]"));
	    if (!elements.isEmpty()) {
	        elements.get(0).click();
	    } else {
	        System.out.println("This test is currently running on IPAD");
	    }
		
		
		driver.findElement(AppiumBy.accessibilityId("Page Control")).click();
		
		WebElement color1 = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePageIndicator[`value == 'page 3 of 10'`]"));
		color1.click();
		System.out.println("The color change, current color is green");
		test.log(LogStatus.INFO, "The color changed, current color is green");
		
		WebElement color2 = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePageIndicator[`value == 'page 4 of 10'`]"));
		color2.click();
		System.out.println("The color change, current color is blue");
		test.log(LogStatus.INFO, "The color changed, current color is blue");
	}

}
