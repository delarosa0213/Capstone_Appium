	package iOS.tests.Pages;
	
	import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
	import org.openqa.selenium.remote.RemoteWebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
	import io.appium.java_client.MobileBy;
	import io.appium.java_client.TouchAction;
	import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
	import java.util.List;
	
	
	public class ScrollGesture_Page {
		
		static IOSDriver driver;
		ExtentTest test;
		private File directory; 
		
		public ScrollGesture_Page(IOSDriver driver, ExtentTest test, File directory) 
		{
	        this.driver = driver;
	        this.test = test;
	        this.directory = directory;
	    }
		
		public void scroll() throws IOException {
			List<WebElement> options = driver.findElements(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText"));
		    int count = 0;
		    for (WebElement option : options) {
		        String label = option.getAttribute("label");
		        if (label != null) {
		            label = label.trim();
		            if (label.equals("Activity Indicators")
		                || label.equals("Alert Views")
		                || label.equals("Buttons")
		                || label.equals("Date Picker")
		                || label.equals("Image View")
		                || label.equals("Page Control")
		                || label.equals("Picker View")
		                || label.equals("Progress Views")
		                || label.equals("Search")
		                || label.equals("Segmented Controls")
		                || label.equals("Sliders")
		                || label.equals("Stack Views")
		                || label.equals("Steppers")
		                || label.equals("Switches")
		                || label.equals("Text Fields")
		                || label.equals("Text View")
		                || label.equals("Toolbars")
		                || label.equals("Web View")) {
		                count++;
		            }
		        }
		    }
		    System.out.println("Number of options: " + count);
		    test.log(LogStatus.INFO, "Number of options: " + count);
			
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String screenshotPath = directory.getAbsolutePath() + "/screenshots/Printing Options.png";
	        FileUtils.copyFile(screenshotFile, new File(screenshotPath));

	        // Log the screenshot in the report
	        test.log(LogStatus.INFO, "Printing Options: " + test.addScreenCapture(screenshotPath));
			}
	
		}
	
	
