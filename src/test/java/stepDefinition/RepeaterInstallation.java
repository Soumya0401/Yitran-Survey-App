package stepDefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ExtentReportManager;
import utilities.driverFactory;

public class RepeaterInstallation {

    AndroidDriver driver = driverFactory.getDriver();
    static ExtentReports extent = ExtentReportManager.getInstance();
    static ExtentTest test;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("Admin selects on the Installation module")
    public void adminClicksInstallation() {
        try {
            test = extent.createTest("Repeater Installation - Access Module");
            WebElement installModule = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Installation']")));
            installModule.click();
            test.pass("✅ Clicked on Installation module successfully");
        } catch (Exception e) {
            test.fail("❌ Failed to click Installation module: " + e.getMessage());
        }
    }

    @And("Admin selects Repeater")
    public void adminSelectsRepeater() {
        try {
            WebElement repeaterOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Repeater']")));
            repeaterOption.click();
            test.pass("✅ Selected Repeater successfully");
        } catch (Exception e) {
            test.fail("❌ Failed to select Repeater: " + e.getMessage());
        }
    }

    @And("Admin selects Subdivision, Feeder, and DTR")
    public void selectSubdivisionFeederDTR() {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ScrollView/android.widget.Spinner[1]"))).click();
                driver.findElement(By.xpath("//android.widget.TextView[@text='BANMALIPUR ESD-II']")).click();

                driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.Spinner[1]")).click();
                driver.findElement(By.xpath("//android.widget.TextView[@text='Central']")).click();

                driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.Spinner[2]")).click();
                driver.findElement(By.xpath("//android.widget.TextView[@text='Ujjayanta Market']")).click();

                test.pass("Selected Subdivision, Feeder, and DTR");
            } catch (Exception e) {
                test.fail("Failed to select Subdivision/Feeder/DTR: " + e.getMessage());
            }
        }
    
    @And("Admin fills and submits all required Repeater installation details")
    public void repeaterInstallationdetails() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Step 1: Click first "Click Now" (Scan QR)
            WebElement scanQR = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//android.widget.TextView[@text='Click Now'])[1]")));
            scanQR.click();
            takePhotoFromCamera(driver);

            Thread.sleep(1500); // Let UI refresh

            // Step 2: Click second "Click Now" for GPS-tagged Repeater photo
            List<WebElement> clickNowButtons = driver.findElements(
                By.xpath("//android.widget.TextView[@text='Click Now']"));

            if (clickNowButtons.size() >= 1) {
                WebElement gpsPhotoBtn = clickNowButtons.get(clickNowButtons.size() - 1); // last Click Now
                //scrollToElement(gpsPhotoBtn); // In case it's off-screen
                gpsPhotoBtn.click();
                takePhotoFromCamera(driver);
            } else {
                test.fail("GPS-tagged photo 'Click Now' button not found.");
                return;
            }

            Thread.sleep(1000); 

            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Submit']")));
            submitBtn.click();

            test.pass("Repeater installation form submitted successfully.");

        } catch (Exception e) {
            test.fail("Failed to submit Repeater installation form: " + e.getMessage());
        }
    }

    public void takePhotoFromCamera(WebDriver driver) {
        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            WebElement shutter = wait.until(ExpectedConditions.elementToBeClickable(
//                By.id("com.android.camera:id/shutter_button")));
//            shutter.click();

            Thread.sleep(1500); 

            WebElement doneBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Capture']")));  // or use id if needed
            doneBtn.click();

            test.info("📸 Photo captured successfully.");
        } catch (Exception e) {
            test.info("⚠️ Camera interaction failed: " + e.getMessage());
        }
      }
    
    }

