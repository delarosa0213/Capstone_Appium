package iOS.tests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class DatePicker_Page {
    static IOSDriver driver;
    ExtentTest test;
    public DatePicker_Page(IOSDriver driver, ExtentTest test) 
    {
        this.driver = driver;
        this.test = test;
    }
    
    public void datePick() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Date Picker")).click();
        
        driver.findElement(AppiumBy.accessibilityId("Date and Time Picker")).click();
        
        boolean pickerWheelSendKeys = false;
        boolean staticTextClickable =  driver.findElement(AppiumBy.accessibilityId("Next Month")).isEnabled();
        
        
        if (staticTextClickable) {
        	driver.findElement(AppiumBy.accessibilityId("Next Month")).click();
            pickerWheelSendKeys = true;
        } else {
            System.out.println("Element not clickable, cannot  change month");
            test.log(LogStatus.FAIL, "Button not clickable, cannot change month");
        }
        
        if (pickerWheelSendKeys) {
            driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[`value == 'May'`]")).sendKeys("June");
        } else {
            System.out.println("Cannot send keys to the element");
            test.log(LogStatus.FAIL, "Cannot change the date!");
        }
        
        Thread.sleep(2000);
        
        driver.findElement(AppiumBy.accessibilityId("PopoverDismissRegion")).click();
        //driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'dismiss popup`]")).click();
    }
        public void timePick() throws InterruptedException {
        //setting time
        Thread.sleep(2000);
       // driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == '11:00 AM'`]")).click();
        driver.findElement(By.xpath("//XCUIElementTypeButton[2]")).click();

        Thread.sleep(2000);
        //driver.findElement(By.xpath("//XCUIElementTypePickerWheel[3]")).sendKeys("PM");
        WebElement pickerWheel = driver.findElement(By.xpath("//XCUIElementTypePickerWheel[3]"));
        String pickerWheelValue = pickerWheel.getAttribute("value");

        if (!pickerWheelValue.equals("PM")) {
            pickerWheel.sendKeys("PM");
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//XCUIElementTypePickerWheel")).sendKeys("7");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//XCUIElementTypePickerWheel[2]")).sendKeys("46");
        
        driver.findElement(AppiumBy.accessibilityId("PopoverDismissRegion")).click();
        
        String buttonText =  driver.findElement(By.xpath("//XCUIElementTypeButton[2]")).getText();
        System.out.println("The time is " + buttonText);
        test.log(LogStatus.INFO,"The time is: " + buttonText);

        }
    
}
