package iOS.tests.Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class TextField_Page {
	static IOSDriver driver;
	ExtentTest test;
	private File directory; 

    
	public TextField_Page(IOSDriver driver, ExtentTest test, File directory) 
	{
        this.driver = driver;
        this.test = test;
        this.directory = directory;
        
    }
	public void txtField(String firstName, String lastName, String email, String iban, String password) throws InterruptedException, IOException {
		
		List<WebElement> elements = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'UIKitCatalog'`]"));
	    if (!elements.isEmpty()) {
	        elements.get(0).click();
	    } else {
	        System.out.println("This test is currently running on IPAD");
	    }
		
		driver.findElement(AppiumBy.accessibilityId("Text Fields")).click();
		

		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == 'Placeholder text'`][1]")).sendKeys(firstName);
		Thread.sleep(2000);
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == 'Placeholder text'`][3]")).sendKeys(lastName);
		Thread.sleep(2000);
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == 'Placeholder text'`][2]")).sendKeys(email);
		Thread.sleep(2000);
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == 'Placeholder text'`]")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//XCUIElementTypeSecureTextField")).sendKeys(password);
		
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = directory.getAbsolutePath() + "/screenshots/Text Fields.png";
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));

        // Log the screenshot in the report
        test.log(LogStatus.INFO, "Text Field: " + test.addScreenCapture(screenshotPath));
		

	}
	
	public void getInfo(String filePath, int row) throws IOException, InterruptedException {
        // Open the Excel file
        FileInputStream file = new FileInputStream(new File(filePath));

        // Get the workbook instance for XLSX file format
        Workbook workbook = WorkbookFactory.create(file);

        // Get the first sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get the values from the Excel sheet
        String firstName = sheet.getRow(row).getCell(2).getStringCellValue();
        String lastName = sheet.getRow(row).getCell(3).getStringCellValue();
        String email = sheet.getRow(row).getCell(0).getStringCellValue();
        Cell ibanCell = sheet.getRow(row).getCell(4);
        String iban = "";
        if(ibanCell.getCellType() == CellType.NUMERIC) {
            iban = String.valueOf((long) ibanCell.getNumericCellValue());
        } else {
            iban = ibanCell.getStringCellValue();
        }
        String password = sheet.getRow(row).getCell(1).getStringCellValue();

        // Close the Excel file
        file.close();

        // Fill in the registration form
        txtField(firstName, lastName, email, iban, password);
        test.log(LogStatus.PASS, "Successfully pass the information!");
    }

}
