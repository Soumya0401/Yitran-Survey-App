package stepDefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import utilities.ExtentReportManager;
import utilities.driverFactory;

public class JNJMaintenance {
	
	AndroidDriver driver = driverFactory.getDriver();
    static ExtentReports extent = ExtentReportManager.getInstance();
    static ExtentTest test;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("Admin opens the Maintenance module for JNJ")
    public void adminSelectsMaintenanceModule() {
        try {
            test = extent.createTest("JNJ Meter Maintenance - Access Module");
            WebElement maintenanceModule = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Maintenance']")));
            maintenanceModule.click();
            test.pass("✅ Clicked on Maintenance module successfully");
        } catch (Exception e) {
            test.fail("❌ Failed to click Maintenance module: " + e.getMessage());
        }
    }

    @And("Admin selects JNJ Meter for Maintenance")
    public void adminselectMaintenance() {
        try {
            WebElement jnjmeterOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='JnJ Meter']")));
            jnjmeterOption .click();
            //Thread.sleep(1000);
            test.pass("✅ Selected JNJ Meter successfully");
        } catch (Exception e) {
            test.fail("❌ Failed to select JNJ Meter: " + e.getMessage());
        }
    }

    @And("Admin selects Subdivision, Feeder, and DTR for Maintenance")
    public void SubdivisionFeederDTRselection() {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText"))).click();
                driver.findElement(By.xpath("//android.widget.TextView[@text='BANMALIPUR ESD-II']")).click();

                driver.findElement(By.xpath("//android.widget.EditText")).click();
                driver.findElement(By.xpath("//android.widget.TextView[@text='Central']")).click();

                driver.findElement(By.xpath("//android.widget.EditText")).click();
                driver.findElement(By.xpath("//android.widget.TextView[@text='Ujjayanta Market']")).click();
                Thread.sleep(1000);

                test.pass("Selected Subdivision, Feeder, and DTR");
            } catch (Exception e) {
                test.fail("Failed to select Subdivision/Feeder/DTR: " + e.getMessage());
            }
        }
    
    @And("Admin clicks on the User ID for Maintenance")
    public void clickonuserId() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
              WebElement searchjnjmeterrId = driver.findElement(By.xpath("//android.widget.EditText"));
              searchjnjmeterrId.click();
              searchjnjmeterrId.sendKeys("8381");
          	
            WebElement searchjnjbtn = driver.findElement(By.xpath("//android.view.View//android.widget.TextView[@text='Search']"));
            searchjnjbtn.click();
            
            WebElement chkdetails = driver.findElement(By.xpath("//d1.k0/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.widget.Button"));
            chkdetails.click();
                    

            test.pass("Checked JNJ Meter For Maintenance");
            

        } catch (Exception e) {
            test.fail("Failed to checked JNJ Meter ID: " + e.getMessage());
        }
    }
    
    @And("Admin fills the JNJ Meter maintenance details")
    public void adminfilljnjMeterdetails() {
        try {
            WebElement maintainencebtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Start New Maintenance']")));
            maintainencebtn .click();
            //Thread.sleep(1000);
            
            WebElement maintainenceactivity = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.Spinner")));
            maintainenceactivity .click();
            
            WebElement maintainenceactivitydropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@text='Power On Reset']")));
            maintainenceactivitydropdown .click();
            
            
            WebElement remarks = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.ScrollView/android.widget.EditText[2]")));
            remarks .click();
            remarks.sendKeys("Need to reset the Power");
            
            WebElement submitbtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@text='Submit']")));
            submitbtn .click();
            
            test.pass("✅ JNJ Meter Maintenance form filled Successfully");
        } catch (Exception e) {
            test.fail("❌ Failed to select JNJ Meter: " + e.getMessage());
        }
    }

}
