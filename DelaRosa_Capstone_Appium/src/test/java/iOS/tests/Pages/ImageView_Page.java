package iOS.tests.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class ImageView_Page {
	
	static IOSDriver driver;
	ExtentTest test;
	
	public ImageView_Page(IOSDriver driver, ExtentTest test) 
	{
        this.driver = driver;
        this.test = test;
    }
	
	public void imageView() throws InterruptedException {
		List<WebElement> elements = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'UIKitCatalog'`]"));
	    if (!elements.isEmpty()) {
	        elements.get(0).click();
	    } else {
	        System.out.println("This test is currently running on IPAD");
	    }
		
		
		driver.findElement(AppiumBy.accessibilityId("Image View")).click();
		
		
		List<WebElement> animatedImages = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeImage[`label == 'Animated'`]"));

		// Wait for 2 seconds to give time for the animation to start
		Thread.sleep(2000);

		String previousImageName = null;
		for (int i = 0; i < 10; i++) { // Repeat the validation for 10 times
		    for (WebElement animatedImage : animatedImages) {
		        String imageName = animatedImage.getAttribute("name");
		        if (imageName != null && !imageName.equals(previousImageName)) {
		            System.out.println("Images are present they are alternating");
		            test.log(LogStatus.INFO, "Images are present they are alternating");
		            previousImageName = imageName;
		            break; // Exit the loop if an alternating image is found
		        }
		    }
		    // Wait for 500 milliseconds before checking for the next alternating image
		    Thread.sleep(500);
		}



	}

}
