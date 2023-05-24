package Android.tests.Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

import io.appium.java_client.android.AndroidDriver;

public class Transaction_Page {
	private AndroidDriver driver;
	ExtentTest test;
	private File directory; 
	
	public Transaction_Page(AndroidDriver driver, ExtentTest test, File directory) {
	    this.driver = driver;
	    this.test = test;
	    this.directory = directory;
	  }
	
	public void transacHistory1(String firstAccName, String iban1, String amount1) throws InterruptedException, IOException {
		
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/btnHistory")).click();
    	Thread.sleep(2000);
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/fabAdd")).click();
    	
    	Thread.sleep(2000);
		
    	//Add 1st acc
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/et_beneficiary2")).sendKeys(firstAccName);
    	test.log(LogStatus.INFO, "Got the data from Excel file: First Account Name");
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/et_accountNumber2")).sendKeys(iban1);
    	test.log(LogStatus.INFO, "Got the data from Excel file: IBAN");
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/et_amount2")).sendKeys(amount1);
    	test.log(LogStatus.INFO, "Got the data from Excel file: amount");
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/btn_send2")).click();
    	

    	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = directory.getAbsolutePath() + "/screenshots/Add_First Acc.png";
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));

        // Log the screenshot in the report
        test.log(LogStatus.INFO, "First Beneficiary Acc: " + test.addScreenCapture(screenshotPath));
    	
		Thread.sleep(2000);
		
	}
		public void transacHistory2(String secAccName, String iban2, String amount2) throws InterruptedException, IOException {
		
		Thread.sleep(1000);
			
	    driver.findElement(By.id("com.example.proiectmobilebanking:id/fabAdd")).click();
	
		Thread.sleep(2000);
			
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/et_beneficiary2")).sendKeys(secAccName);
    	test.log(LogStatus.INFO, "Got the data from Excel file: Second Account Name");
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/et_accountNumber2")).sendKeys(iban2);
    	test.log(LogStatus.INFO, "Got the data from Excel file: IBAN");
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/et_amount2")).sendKeys(amount2);
    	test.log(LogStatus.INFO, "Got the data from Excel file: amount");
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/spinner_status")).click();
    	driver.findElement(By.xpath("(//android.widget.TextView)[2]")).click();
    	
    	test.log(LogStatus.INFO, "Change from 'Send' to 'Ask'");
    	
    	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = directory.getAbsolutePath() + "/screenshots/Add_Second Acc.png";
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));

        // Log the screenshot in the report
        test.log(LogStatus.INFO, "Second Beneficiary Acc: " + test.addScreenCapture(screenshotPath));
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/btn_send2")).click();
    	
    	
    	Thread.sleep(2000);
    	
    	String firstAcc = driver.findElement(By.xpath("(//android.widget.TextView)[1]")).getText();
    	System.out.println("Validated First Account: " + firstAcc);

    	if (firstAcc != null && !firstAcc.isEmpty()) {
    	    test.log(LogStatus.PASS, "Validated First Account: " + firstAcc);
    	} else {
    	    test.log(LogStatus.FAIL, "No account found");
    	}

    	driver.findElement(By.xpath("(//android.view.ViewGroup)[4]")).click();
    	String secAcc = driver.findElement(By.xpath("(//android.widget.TextView)[5]")).getText();
    	System.out.println("Validated Second Account: " + secAcc);
    	

    	if (secAcc != null && !secAcc.isEmpty()) {
    	    test.log(LogStatus.PASS, "Validated First Account: " + secAcc);
    	} else {
    	    test.log(LogStatus.FAIL, "No account found");
    	}
    	
    	Thread.sleep(2000);
    	File screenshotFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath1 = directory.getAbsolutePath() + "/screenshots/Validation of two Acc.png";
        FileUtils.copyFile(screenshotFile1, new File(screenshotPath1));

        // Log the screenshot in the report
        test.log(LogStatus.INFO, "Beneficiary Account: " + test.addScreenCapture(screenshotPath1));
    }

	
	public void getUserFromExcel1(String filePath, int row2) throws IOException, InterruptedException {
        // Open the Excel file
        FileInputStream file = new FileInputStream(new File(filePath));

        // Get the workbook instance for XLSX file format
        Workbook workbook = WorkbookFactory.create(file);

        // Get the first sheet
        Sheet sheet = workbook.getSheetAt(0);
        


        // Get the values from the Excel sheet first Acc
        String firstAccName = sheet.getRow(row2).getCell(0).getStringCellValue();
        
        Cell ibanCell = sheet.getRow(row2).getCell(1);
        String iban1 = "";
        if(ibanCell.getCellType() == CellType.NUMERIC) {
            iban1 = String.valueOf((long) ibanCell.getNumericCellValue());
        } else {
            iban1 = ibanCell.getStringCellValue();
        }
        
        Cell amountCell = sheet.getRow(row2).getCell(2);
        String amount1 = "";
        if(amountCell.getCellType() == CellType.NUMERIC) {
        	amount1 = String.valueOf((long) amountCell.getNumericCellValue());
        } else {
        	amount1 = amountCell.getStringCellValue();
        }

        // Fill in the registration form
        transacHistory1(firstAccName, iban1, amount1);
        test.log(LogStatus.PASS, "Successfully got the data from excel");

	    
	}
        
      public void getUserFromExcel2(String filePath, int row3) throws IOException, InterruptedException {
            // Open the Excel file
    	FileInputStream file = new FileInputStream(new File(filePath));

            // Get the workbook instance for XLSX file format
       	Workbook workbook = WorkbookFactory.create(file);

            // Get the first sheet
       	Sheet sheet = workbook.getSheetAt(0);        
        //2nd beneficiary acc
        String secAccName = sheet.getRow(row3).getCell(0).getStringCellValue();
        
        Cell ibanCell2 = sheet.getRow(row3).getCell(1);
        String iban2 = "";
        if(ibanCell2.getCellType() == CellType.NUMERIC) {
            iban2 = String.valueOf((long) ibanCell2.getNumericCellValue());
        } else {
            iban2 = ibanCell2.getStringCellValue();
        }
        
        
        Cell amountCell2 = sheet.getRow(row3).getCell(2);
        String amount2 = "";
        if(amountCell2.getCellType() == CellType.NUMERIC) {
        	amount2 = String.valueOf((long) amountCell2.getNumericCellValue());
        } else {
        	amount2 = amountCell2.getStringCellValue();
        }

        // Close the Excel file
        file.close();
        
        transacHistory2(secAccName, iban2, amount2);
        test.log(LogStatus.PASS, "Successfully got the data from excel");

       
 }

    public void transactions(String amountSearch) throws InterruptedException, IOException {
    	driver.navigate().back();
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/btnRaport")).click();
		
    	Thread.sleep(3000);
    	
		driver.findElement(By.id("com.example.proiectmobilebanking:id/btnRaportAmount")).click();
		
		Thread.sleep(3000);
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/etRaportAmount")).sendKeys(amountSearch);
    	test.log(LogStatus.INFO, "Got the data from Excel file");
    	
    	driver.findElement(By.id("com.example.proiectmobilebanking:id/btnViewRaportAmount")).click();
    	
    	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = directory.getAbsolutePath() + "/screenshots/Add_Second Acc.png";
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));

        // Log the screenshot in the report
        test.log(LogStatus.INFO, "View Raport: " + test.addScreenCapture(screenshotPath));
    	
    	//validating 1st account
    	WebElement firstAcc = driver.findElement(By.xpath("(//android.widget.TextView)[1]"));
    	if(firstAcc.isDisplayed()) {
    	    System.out.println("Element is displayed");
    	} else {
    	    System.out.println("Element is not displayed");
    	}
    	
    	//validating 2nd acc
    	WebElement secAcc = driver.findElement(By.xpath("(//android.widget.TextView)[5]"));
    	if(secAcc.isDisplayed()) {
    	    System.out.println("Element is displayed");
    	} else {
    	    System.out.println("Element is not displayed");
    	}
    	 
}
    public void searchAmount(String filePath, int row4) throws IOException, InterruptedException{
    	// Open the Excel file
        FileInputStream file = new FileInputStream(new File(filePath));

        // Get the workbook instance for XLSX file format
        Workbook workbook = WorkbookFactory.create(file);

        // Get the first sheet
        Sheet sheet = workbook.getSheetAt(0);
        
        Cell amountCell3 = sheet.getRow(row4).getCell(0);
        String amountSearch = "";
        if(amountCell3.getCellType() == CellType.NUMERIC) {
        	amountSearch = String.valueOf((long) amountCell3.getNumericCellValue());
        } else {
        	amountSearch = amountCell3.getStringCellValue();
        }
        file.close();
        transactions(amountSearch);
        test.log(LogStatus.PASS, "Successfully got the data from excel");
	}
    
}