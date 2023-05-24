package iOS.tests.Pages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class AlertViews_Page {
	static IOSDriver driver;
	ExtentTest test; 
	private File directory; 
	
	public AlertViews_Page(IOSDriver driver, ExtentTest test, File directory) {
        this.driver = driver;
        this.test = test;
        this.directory = directory;
    }
	
	public void alertViews() throws InterruptedException, IOException {
		List<WebElement> elements = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'UIKitCatalog'`]"));
	    if (!elements.isEmpty()) {
	        elements.get(0).click();
	    } else {
	        System.out.println("This test is currently running on IPAD");
	    }
		
		
		driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
		
		driver.findElement(AppiumBy.accessibilityId("Simple")).click();
		
		System.out.println("The message box has been shown.");
		
		WebElement title = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'A Short Title Is Best'`]"));
		boolean titleDisplayed = title.isDisplayed();
		if(titleDisplayed) {
			System.out.println("The title of the message box is correct.");
			test.log(LogStatus.INFO, "The title of the message box is correct.");
			test.log(LogStatus.INFO, "The title is: " + title.getText());
			
		}
		
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = directory.getAbsolutePath() + "/screenshots/Alert Views.png";
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));

        // Log the screenshot in the report
        test.log(LogStatus.INFO, "Alert Views: " + test.addScreenCapture(screenshotPath));
		
		Thread.sleep(2000);
		
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"OK\"`]")).click();
	}
}
