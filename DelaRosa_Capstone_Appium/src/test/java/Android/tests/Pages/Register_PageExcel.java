package Android.tests.Pages;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Register_PageExcel {
    private AndroidDriver driver;
    ExtentTest test;
    private File directory; 

    public Register_PageExcel(AndroidDriver driver, ExtentTest test, File directory) {
        this.driver = driver;
        this.test = test;
        this.directory = directory;
    }

    public void btnReg(String firstName, String lastName, String email, String iban, String password) throws InterruptedException, IOException {
        driver.findElement(By.id("com.example.proiectmobilebanking:id/register_button_main")).click();

        Thread.sleep(2000);

        driver.findElement(By.id("com.example.proiectmobilebanking:id/et_firstName")).sendKeys(firstName);
        test.log(LogStatus.INFO, "Successfully get the data 'FIRSTNAME' from excel file");

        driver.findElement(By.id("com.example.proiectmobilebanking:id/et_lastName")).sendKeys(lastName);
        test.log(LogStatus.INFO, "Successfully get the data 'LASTNAME' from excel file");

        driver.findElement(By.id("com.example.proiectmobilebanking:id/et_email")).sendKeys(email);
        test.log(LogStatus.INFO, "Successfully get the data 'EMAIL' from excel file");

        driver.findElement(By.id("com.example.proiectmobilebanking:id/et_iban")).sendKeys(iban);
        test.log(LogStatus.INFO, "Successfully get the data 'IBAN' from excel file");

        driver.findElement(By.id("com.example.proiectmobilebanking:id/et_password")).sendKeys(password);
        test.log(LogStatus.INFO, "Successfully get the data 'PASSWORD' from excel file");

        driver.findElement(By.id("com.example.proiectmobilebanking:id/et_confirmPassword")).sendKeys(password);
        test.log(LogStatus.INFO, "Successfully get the data 'PASSWORD' from excel file and used it in confirming password!");

        driver.findElement(By.id("com.example.proiectmobilebanking:id/rb_male")).click();

        Thread.sleep(2000);
        
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = directory.getAbsolutePath() + "/screenshots/Registration_Page.png";
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));

        // Log the screenshot in the report
        test.log(LogStatus.INFO, "Registration Page: " + test.addScreenCapture(screenshotPath));

        driver.findElement(By.id("com.example.proiectmobilebanking:id/bt_register")).click();
    }


    public void registerUserFromExcel(String filePath, int row) throws IOException, InterruptedException {
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
        btnReg(firstName, lastName, email, iban, password);
        test.log(LogStatus.PASS, "Successfully pass the information!");
    }
}
