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

public class ActivityIndicator_Page {
	static IOSDriver driver;
	ExtentTest test;
	private static File directory;
	
	public ActivityIndicator_Page(IOSDriver driver, ExtentTest test, File directory) {
        this.driver = driver;
        this.test = test;
        this.directory = directory;
    }
	
	public void actvityIndi() throws IOException, InterruptedException {
		driver.findElement (AppiumBy.accessibilityId("Activity Indicators")).click();
		
		Thread.sleep(2000);
		

		test.log(LogStatus.INFO, "The activity indicator appeared!");

		
		
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = directory.getAbsolutePath() + "/screenshots/Activity indicator.png";
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));

        // Log the screenshot in the report
        test.log(LogStatus.INFO, "Activity indicator: " + test.addScreenCapture(screenshotPath));
        
        Thread.sleep(2000);
        
        List<WebElement> elements = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'UIKitCatalog'`]"));
	    if (!elements.isEmpty()) {
	        elements.get(0).click();
	    } else {
	        System.out.println("This test is currently running on IPAD");
	    }
		

	}

}
