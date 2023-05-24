package Android.tests.Test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Android.tests.Pages.AccountDeletion_Page;
import Android.tests.Pages.Login_Page;
import Android.tests.Pages.Register_PageExcel;
import Android.tests.Pages.Transaction_Page;

public class AppiumAndroidPhoneMain {
	private AndroidDriver driver;
	private static File directory;
	static ExtentReports extent;
    static ExtentTest test;
    
	 private LaunchingAndroidDevice base;
	 private Register_PageExcel reg;
	 private Login_Page login;
	 private Transaction_Page transact;
	 private AccountDeletion_Page accDel;

	 @BeforeClass
	 public void setup() {
		 String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
	     String folderName = "AndroidPhoneTest-" + timeStamp;
	     directory = new File("results/Android/Android Phone/" + folderName);
      if (!directory.exists()) {
    	  directory.mkdirs();
	 }
	     extent = new ExtentReports(directory.getAbsolutePath() + "/AndroidTestResult.html");
	     test  = extent.startTest("PocketBank Automation Testing");
	        
	 }
	 @AfterTest
	 public void tearDown() {
		 //flushing and closing extent report
	     extent.flush();
	     extent.close();
     }
	 @Test(priority = 1)
	 public void applLaunch() throws MalformedURLException, FileNotFoundException {
		//launching the app
		 base = new LaunchingAndroidDevice();
		 driver = base.configureAndroidPhone();
		 test.log(LogStatus.PASS, "Launched app successfully!");
  }
	 @Test(priority = 2)
	 public void Register() throws IOException, InterruptedException {
		 //registering a user
	     Register_PageExcel reg = new Register_PageExcel(driver, test, directory);
	     //reg.btnReg(firstName, lastName, email, iban, password, directory);
	     reg.registerUserFromExcel("src/test/java/utils/loginCredentials.xlsx", 1);
	     
	 }

	 @Test(priority = 3)
	 public void Login() throws IOException, InterruptedException {
		 //logging in using valid credentials
		 Login_Page login =  new Login_Page(driver, test, directory);
		 login.getUserFromExcel("src/test/java/utils/loginCredentials.xlsx", 1);
		 test.log(LogStatus.PASS, "Login Successfully!");
	 }
	 @Test(priority = 4)
	 public void transactHistory() throws IOException, InterruptedException {
		 //performing transactions, adding first beneficiary
		 Transaction_Page transact = new Transaction_Page(driver, test, directory);
		 transact.getUserFromExcel1("src/test/java/utils/beneficiary accs.xlsx", 1);
		 test.log(LogStatus.PASS, "First Account Added!");
	 }
	 @Test(priority = 5)
	 public void transactHistory2() throws IOException, InterruptedException {
		//performing transactions, adding second beneficiary
		 Transaction_Page transact = new Transaction_Page(driver, test, directory);
		 transact.getUserFromExcel2("src/test/java/utils/beneficiary accs.xlsx", 2);
		 test.log(LogStatus.PASS, "Second Account Added!");
	 }
	 @Test(priority = 6)
	 public void trans() throws IOException, InterruptedException {
		 //performing filter
		 Transaction_Page transact = new Transaction_Page(driver, test, directory);
		 transact.searchAmount("src/test/java/utils/raport filter.xlsx", 1);
		 test.log(LogStatus.INFO, "Amount transfered reports");
	 }	
	 @Test(priority = 7)
	 public void delAccount() {
		 //performing account deletion
		 AccountDeletion_Page accDel = new AccountDeletion_Page(driver, test);
		 accDel.DeleteAcc();
		 test.log(LogStatus.PASS, "The account deleted successfully!");
	 }
	 @Test(priority = 8)
	 public void testLogin() throws IOException, InterruptedException {
		 //performing login after account deletion
		 Login_Page login =  new Login_Page(driver, test, directory);
		 login.getUserFromExcel("src/test/java/utils/loginCredentials.xlsx", 1);
		 test.log(LogStatus.PASS, "The account was deleted, please create a new account!");
		 extent.endTest(test);
	 }
	 
	 @AfterClass
	 public void quit() {
		 //quitting Appium driver instance
		 driver.quit();
	 }
	
}
