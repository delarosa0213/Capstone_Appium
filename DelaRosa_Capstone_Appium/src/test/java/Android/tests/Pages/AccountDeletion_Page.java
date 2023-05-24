package Android.tests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class AccountDeletion_Page {
	private AndroidDriver driver;
	ExtentTest test;

	public AccountDeletion_Page(AndroidDriver driver, ExtentTest test) {
	    this.driver = driver;
	    this.test = test;
	  }
	
	public void DeleteAcc() {
    	driver.navigate().back();
    	driver.navigate().back();
    	
    	WebElement delBtn = driver.findElement(By.id("com.example.proiectmobilebanking:id/btnDeleteAccount"));
    	//checking if the delBtn is present
    	if (delBtn.isDisplayed()) {
    		delBtn.click();
    		test.log(LogStatus.PASS, "Account Deleted Successfully!");
    	}
    	else {
    		System.out.println("Element is not available");
    		test.log(LogStatus.FAIL, "Account Deletion denied!");
    	}

}
}
