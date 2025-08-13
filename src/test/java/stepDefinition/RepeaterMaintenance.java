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

public class RepeaterMaintenance
{
	AndroidDriver driver = driverFactory.getDriver();
    static ExtentReports extent = ExtentReportManager.getInstance();
    static ExtentTest test;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("Admin selects the Repeater Maintenance module")
    public void adminSelectsrepeaterMaintenanceModule() {
        try {
            test = extent.createTest("Repeater Maintenance - Access Module");
            WebElement maintenanceModule = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Maintenance']")));
            maintenanceModule.click();
            test.pass("✅ Clicked on Maintenance module successfully");
        } catch (Exception e) {
            test.fail("❌ Failed to click Maintenance module: " + e.getMessage());
        }
    }

    @And("Admin selects Repeater for Maintenance")
    public void adminselectMaintenance() {
        try {
            WebElement repeaterOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Repeater']")));
            repeaterOption .click();
            //Thread.sleep(1000);
            test.pass("✅ Selected Repeater successfully");
        } catch (Exception e) {
            test.fail("❌ Failed to select Repeater: " + e.getMessage());
        }
    }

    @And("Admin selects Subdivision, Feeder, and DTR for Repeator Maintenance")
    public void SubdivisionFeederDTR() {
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
    
    @And("Admin clicks on the Repeater ID for Maintenance")
    public void clickonreapeaterId() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
              WebElement searchdrepeaterId = driver.findElement(By.xpath("//android.widget.EditText"));
              searchdrepeaterId.click();
              searchdrepeaterId.sendKeys("17");
          	
            WebElement searchrepeaterbtn = driver.findElement(By.xpath("//android.view.View//android.widget.TextView[@text='Search']"));
            searchrepeaterbtn.click();
            
            WebElement chkdetails = driver.findElement(By.xpath("//d1.k0/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.widget.Button"));
            chkdetails.click();
                    

            test.pass("Checked Repeater Id For Maintenance");
            

        } catch (Exception e) {
            test.fail("Failed to checked Repeater ID: " + e.getMessage());
        }
    }
    
    @And("Admin fills the Repeater maintenance details")
    public void adminfillRepeaterdetails() {
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
            
            test.pass("✅ Repeater Maintenance form filled Successfully");
        } catch (Exception e) {
            test.fail("❌ Failed to select Repeater: " + e.getMessage());
        }
    }

}
