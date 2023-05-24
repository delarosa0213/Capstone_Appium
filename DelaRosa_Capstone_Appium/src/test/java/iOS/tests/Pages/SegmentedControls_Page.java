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

public class SegmentedControls_Page {
	
	static IOSDriver driver;
	ExtentTest test;
	private File directory; 
	
	public SegmentedControls_Page(IOSDriver driver, ExtentTest test, File directory) 
	{
        this.driver = driver;
        this.test = test;
        this.directory = directory;
    }
	
	public void segmentedControl() throws IOException, InterruptedException {
		List<WebElement> elements = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'UIKitCatalog'`]"));
	    if (!elements.isEmpty()) {
	        elements.get(0).click();
	    } else {
	        System.out.println("This test is currently running on IPAD");
	    }
		
		
		driver.findElement(AppiumBy.accessibilityId("Segmented Controls")).click();
		
		File screenshotFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath1 = directory.getAbsolutePath() + "/screenshots/Segmented Control before clicking.png";
        FileUtils.copyFile(screenshotFile1, new File(screenshotPath1));

        // Log the screenshot in the report
        test.log(LogStatus.INFO, "Before Clicking: " + test.addScreenCapture(screenshotPath1));
		
		//default select tools
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Tools'`][1]")).click();
		test.log(LogStatus.INFO, "Selected 'Tools'!");
		
		//check in tinted 
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Check'`][2]")).click();
		test.log(LogStatus.INFO, "Selected 'Check'");
		
		//gift
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Gift'`]")).click();
		test.log(LogStatus.INFO, "Selected 'Gift' icon!");
		
		File screenshotFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath2 = directory.getAbsolutePath() + "/screenshots/Segmented Control after clicking.png";
        FileUtils.copyFile(screenshotFile2, new File(screenshotPath2));

        // Log the screenshot in the report
        test.log(LogStatus.INFO, "After Clicking: " + test.addScreenCapture(screenshotPath2));
        
        Thread.sleep(2000);
	}

}
