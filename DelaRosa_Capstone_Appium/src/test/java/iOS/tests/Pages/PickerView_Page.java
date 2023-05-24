package iOS.tests.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class PickerView_Page {
	static IOSDriver driver;
	ExtentTest test;
	
	public PickerView_Page(IOSDriver driver, ExtentTest test) 
	{
        this.driver = driver;
        this.test =  test;
    }
	public void pickerView() {
		
	List<WebElement> elements = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'UIKitCatalog'`]"));
	  if (!elements.isEmpty()) {
       elements.get(0).click();
    } else {
	   System.out.println("This test is currently running on IPAD");
	        
    }
		
    
    
	driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Picker View'`]")).click();
	
	WebElement RedColor = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[`label == 'Red color component value'`]"));
	RedColor.sendKeys("155");
	test.log(LogStatus.INFO, "Red attribute change to 155!");
	
	WebElement GreenColor = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[`label == 'Green color component value'`]"));
	GreenColor.sendKeys("50");
	test.log(LogStatus.INFO, "Green attribute change to 50!");

	WebElement BlueColor = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[`label == 'Blue color component value'`]"));
	BlueColor.sendKeys("125");
	test.log(LogStatus.INFO, "Blue attribute change to 125!");

	
	System.out.println("The color changed!");

}
}