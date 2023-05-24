package iOS.tests.Test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.ios.IOSDriver;

import iOS.tests.Pages.ScrollGesture_Page;
import iOS.tests.Pages.ActivityIndicator_Page;
import iOS.tests.Pages.DatePicker_Page;
import iOS.tests.Pages.ImageView_Page;
import iOS.tests.Pages.PageControl_Page;
import iOS.tests.Pages.PickerView_Page;
import iOS.tests.Pages.ProgressView_Page;
import iOS.tests.Pages.Search_Page;
import iOS.tests.Pages.SegmentedControls_Page;
import iOS.tests.Pages.Slider_Page;
import iOS.tests.Pages.StackViews_Page;
import iOS.tests.Pages.Switch_Page;
import iOS.tests.Pages.TextField_Page;
import iOS.tests.Pages.Toolbars_Page;
import iOS.tests.Pages.WebViews_Page;
import iOS.tests.Pages.AlertViews_Page;

public class AppiumIpadMain {
	
	static IOSDriver driver;
	static ExtentReports extent;
    static ExtentTest test;
    private static File directory;
  

    @BeforeTest
    public void setUp() {
    	String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        String folderName = "iPadTest-" + timeStamp;
        directory = new File("results/iOS/iPAD/" + folderName);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        extent = new ExtentReports(directory.getAbsolutePath() + "/TestResult.html");
        test  = extent.startTest("iOS testing UIKit Catalog");
	    
	    }
	    @AfterTest
	    public void tearDown() {
	        extent.flush();
	        extent.close();
	    }
    
	  @Test(priority =1)	
	  public void setup() throws MalformedURLException, FileNotFoundException {
		  LaunchIOSDevices ipad = new LaunchIOSDevices();
		  ipad.configureIpad();
		  driver = LaunchIOSDevices.driver;
		  test.log(LogStatus.PASS, "Application successfully launched!");
	  }
	  @Test(priority = 2)
	  public void scrollGes() throws IOException {
		  ScrollGesture_Page scroll = new ScrollGesture_Page(driver, test, directory);
		  scroll.scroll();
		  test.log(LogStatus.PASS, "Printing options count retrieved successfully");
		  
	  }
	  @Test(priority = 3)
	  public void actIndicator() throws IOException, InterruptedException {
		  ActivityIndicator_Page act = new ActivityIndicator_Page(driver, test, directory);
		  act.actvityIndi();
		  test.log(LogStatus.PASS, "Activity Indicator visible");
	  }
	  @Test(priority = 4)
	  public void datePicker() throws InterruptedException {
		  DatePicker_Page date= new DatePicker_Page(driver, test);
		  date.datePick();
		  test.log(LogStatus.INFO, "Date cannot be change");
	  }
	  @Test(priority = 5)
	  public void timePicker() throws InterruptedException {
		  DatePicker_Page time= new DatePicker_Page(driver, test);
		  time.timePick();
		  test.log(LogStatus.PASS, "Time changed!");
	  }
	  @Test(priority = 6)
	  public void ImageView() throws InterruptedException {
		  ImageView_Page img= new ImageView_Page(driver, test);
		  img.imageView();  
		  test.log(LogStatus.PASS, "Images are present they are alternating");
	  }
	  @Test(priority = 7)
	  public void pageControl(){
		  PageControl_Page pc= new PageControl_Page(driver, test);
		  pc.pageControl();
		  test.log(LogStatus.PASS, "Colors changed!");
	  }
	  @Test(priority = 8)
	  public void pickView() {
		  PickerView_Page pv = new PickerView_Page(driver, test);
		  pv.pickerView();
		  test.log(LogStatus.PASS, "Color changed!");
	  }
	  @Test(priority = 9)
	  public void progView() throws InterruptedException {
		  ProgressView_Page prog = new ProgressView_Page(driver, test);
		  prog.progressView();
		  test.log(LogStatus.PASS, "Progress view finished!");
	  }
	  @Test(priority = 10)
	  public void searchView() throws InterruptedException {
		  Search_Page srch = new Search_Page(driver, test);
		  srch.search();
		  test.log(LogStatus.PASS, "Successfully typed letters");
	  }
	  @Test(priority = 11)
	  public void segmentedControls() throws IOException, InterruptedException {
		  SegmentedControls_Page sp = new SegmentedControls_Page(driver, test, directory);
		  sp.segmentedControl();
		  test.log(LogStatus.PASS, "Successfully toggled all the button!");
	  }
	  @Test(priority = 12)
	  public void sliderControl() {
		  Slider_Page sld = new Slider_Page(driver, test);
		  sld.slide();
		  test.log(LogStatus.PASS, "Successfully changed all the sliders!");
	  }
	  @Test(priority = 13)
	  public void stackViews() throws InterruptedException {
		  StackViews_Page svp = new StackViews_Page(driver, test);
		  svp.stackView();
		  test.log(LogStatus.PASS, "Test Successfully executed!");
	  }
	  
	  @Test(priority = 14)
	  public void switches() throws InterruptedException{
		  Switch_Page swp = new Switch_Page(driver, test);
		  swp.switchPage();
		  test.log(LogStatus.PASS, "Both switches toggled!");
	  }
	  @Test(priority = 15)
	  public void textField() throws InterruptedException, IOException{
		  TextField_Page txtF = new TextField_Page(driver, test, directory);
		  txtF.getInfo("src/test/java/utils/loginCredentials.xlsx", 1);
		  test.log(LogStatus.PASS, "Test Successfully executed!");
	  }
	  @Test(priority = 16)
	  public void toolBars(){
		  Toolbars_Page tools = new Toolbars_Page(driver, test);
		  tools.toolBars();
		  test.log(LogStatus.PASS, "Test Successfully executed!");
	  }
	  
	  @Test(priority = 17)
	  public void webViews() throws InterruptedException{
		  WebViews_Page web = new WebViews_Page(driver, test);
		  web.webViews();
		  test.log(LogStatus.PASS, "Test Successfully executed!");
	  }
	  @Test(priority = 18)
	  public void alert() throws InterruptedException, IOException{
		  AlertViews_Page al = new AlertViews_Page(driver, test, directory);
		  al.alertViews();
		  test.log(LogStatus.PASS, "Test Successfully executed!");
		  extent.endTest(test);
	  }
	  
	}
