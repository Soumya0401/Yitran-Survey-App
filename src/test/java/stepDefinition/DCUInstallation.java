//package stepDefinition;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.Random;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//
//import io.appium.java_client.TouchAction;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.touch.WaitOptions;
//import io.appium.java_client.touch.offset.PointOption;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import utilities.ExtentReportManager;
//import utilities.driverFactory;
//
//public class DCUInstallation {
//
//    AndroidDriver driver = driverFactory.getDriver();
//    static ExtentReports extent = ExtentReportManager.getInstance();
//    static ExtentTest test;
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//    @When("Admin clicks on the Installation module")
//    public void adminClicksInstallation() {
//        try {
//            test = extent.createTest("DCU Installation - Access Module");
//            WebElement installModule = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//android.widget.TextView[@text='Installation']")));
//            installModule.click();
//            test.pass("Clicked on Installation module successfully");
//        } catch (Exception e) {
//            test.fail("Failed to click Installation module: " + e.getMessage());
//        }
//    }
//
//    @And("Admin selects DCU")
//    public void adminSelectsDCU() {
//        try {
//            WebElement dcuOption = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//android.widget.TextView[@text='DCU']")));
//            dcuOption.click();
//            test.pass("Selected DCU successfully");
//        } catch (Exception e) {
//            test.fail("Failed to select DCU: " + e.getMessage());
//        }
//    }
//
//    @And("Admin selects a valid Subdivision, Feeder, and DTR")
//    public void selectSubdivisionFeederDTR() {
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ScrollView/android.widget.Spinner[1]"))).click();
//            driver.findElement(By.xpath("//android.widget.TextView[@text='BANMALIPUR ESD-II']")).click();
//
//            driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.Spinner[1]")).click();
//            driver.findElement(By.xpath("//android.widget.TextView[@text='Central']")).click();
//
//            driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.Spinner[2]")).click();
//            driver.findElement(By.xpath("//android.widget.TextView[@text='Ujjayanta Market']")).click();
//
//            test.pass("Selected Subdivision, Feeder, and DTR");
//        } catch (Exception e) {
//            test.fail("Failed to select Subdivision/Feeder/DTR: " + e.getMessage());
//        }
//    }
//
//    @And("Admin randomly selects Power On status")
//    public void randomPowerStatusSelection() {
//        try {
//            scrollUp(driver); // Ensure dropdown is in view
//
//            WebElement powerDropdown = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//android.widget.ScrollView/android.widget.Spinner[3]")));
//            powerDropdown.click();
//
//            Thread.sleep(1000); // Let the dropdown open
//
//            String[] options = {"Yes", "No"};
//            String status = options[new Random().nextInt(options.length)];
//            test.info("Randomly selected Power On status: " + status);
//
//            WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//android.widget.TextView[@text='" + status + "']")));
//            statusOption.click();
//
//            // Scroll again if needed
//            scrollUp(driver);
//
//            // Fill the form accordingly
//            if (status.equalsIgnoreCase("Yes")) {
//                fillFormForPowerOnYes();
//            } else {
//                fillFormForPowerOnNo();
//            }
//
//        } catch (Exception e) {
//            test.fail("Failed to randomly select Power On status: " + e.getMessage());
//        }
//    }
//
//    @Then("Form is filled based on Power On status")
//    public void formFilledMessage() {
//        test.pass("Form was filled successfully based on randomly selected Power On status.");
//    }
//
//    public void fillFormForPowerOnYes() {
//        try {
//            // Step 1: Upload QR image of DCU
//            WebElement qrScan = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("(//android.widget.TextView[@text='Click Now'])[1]")));
//            qrScan.click();
//            takePhotoFromCamera();
//
//            // ✅ Wait a bit or re-stabilize the screen before next action
//            Thread.sleep(1000);  // Allow UI to refresh
//
//            // Step 2: Re-locate the next "Click Now" button for DCU with GPS
//            List<WebElement> clickNowButtons = driver.findElements(By.xpath("//android.widget.TextView[@text='Click Now']"));
//
//            if (clickNowButtons.size() >= 1) {
//                WebElement dcuPhoto = clickNowButtons.get(clickNowButtons.size() - 1); // get last one
//                dcuPhoto.click();
//                takePhotoFromCamera();
//            } else {
//                test.fail("Second 'Click Now' button not found.");
//                return;
//            }
//
//            // ✅ Scroll up to bring Submit button
//            scrollUp(driver);
//
//            // Step 3: Submit
//            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//android.widget.TextView[@text='Submit']")));
//            submitBtn.click();
//
//            test.pass("Form filled and submitted successfully for Power On: YES");
//
//        } catch (Exception e) {
//            test.fail("Failed to fill form for Power On YES: " + e.getMessage());
//        }
//    }
//
//    public void fillFormForPowerOnNo() {
//        try {
//            // Step 1: Upload Faulty DCU QR image
//            WebElement faultyQR = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("(//android.widget.TextView[@text='Click Now'])[1]")));
//            faultyQR.click();
//            takePhotoFromCamera();
//            Thread.sleep(1000); // Let UI refresh
//
//            // Step 2: Upload Replaced DCU QR image (new layout, index resets)
//            WebElement replacedQR = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("(//android.widget.TextView[@text='Click Now'])[1]")));
//            replacedQR.click();
//            takePhotoFromCamera();
//            Thread.sleep(1000); // Let UI refresh again
//
//            // Step 3: Upload DCU Photo with GPS tagging
//            // Re-locate all "Click Now" buttons again after screen reload
//            List<WebElement> clickNowButtons = driver.findElements(
//                By.xpath("//android.widget.TextView[@text='Click Now']"));
//
//            if (clickNowButtons.size() >= 1) {
//                WebElement gpsPhotoClickNow = clickNowButtons.get(clickNowButtons.size() - 1); // Last one assumed to be GPS
//               // scrollToElement(gpsPhotoClickNow);  // Scroll if offscreen
//                gpsPhotoClickNow.click();
//                takePhotoFromCamera();
//            } else {
//                test.fail("GPS tagging 'Click Now' button not found.");
//                return;
//            }
//
//            Thread.sleep(1000);
//
//            // Step 4: Scroll up and submit
//            scrollUp(driver);
//
//            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//android.widget.TextView[@text='Submit']")));
//            submitBtn.click();
//
//            test.pass("Form filled and submitted successfully for Power On: NO");
//
//        } catch (Exception e) {
//            test.fail("Failed to fill form for Power On NO: " + e.getMessage());
//        }
//    }
//
//
//    public void scrollUp(AndroidDriver driver) {
//        try {
//            int height = driver.manage().window().getSize().height;
//            int width = driver.manage().window().getSize().width;
//
//            int startX = width / 2;
//            int startY = (int) (height * 0.8);  // Start lower
//            int endY = (int) (height * 0.3);    // Move upward
//
//            new TouchAction<>(driver)
//                .press(PointOption.point(startX, startY))
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
//                .moveTo(PointOption.point(startX, endY))
//                .release()
//                .perform();
//
//            test.pass("Scrolled up successfully.");
//        } catch (Exception e) {
//            test.fail("Scroll up failed: " + e.getMessage());
//        }
//    }
//
//    public void takePhotoFromCamera() {
//        try {
////            WebElement shutter = driver.findElement(By.id("com.android.camera:id/shutter_button"));
////            shutter.click();
////            Thread.sleep(1500); 
//
//            WebElement doneBtn = driver.findElement(By.xpath("//android.widget.TextView[@text='Capture']"));
//            doneBtn.click();
//        } catch (Exception e) {
//            test.info("⚠️ Camera interaction skipped or failed.");
//        }
//    }
//}

package stepDefinition;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ExtentReportManager;
import utilities.driverFactory;

public class DCUInstallation {

    AndroidDriver driver = driverFactory.getDriver();
    static ExtentReports extent = ExtentReportManager.getInstance();
    static ExtentTest test;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("Admin clicks on the Installation module")
    public void adminClicksInstallation() {
        try {
            test = extent.createTest("DCU Installation - Access Module");
            WebElement installModule = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@text='Installation']")));
            installModule.click();
            test.pass("Clicked on Installation module successfully");
        } catch (Exception e) {
            test.fail("Failed to click Installation module: " + e.getMessage());
        }
    }

    @And("Admin selects DCU")
    public void adminSelectsDCU() {
        try {
            WebElement dcuOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@text='DCU']")));
            dcuOption.click();
            test.pass("Selected DCU successfully");
        } catch (Exception e) {
            test.fail("Failed to select DCU: " + e.getMessage());
        }
    }

    @And("Admin selects a valid Subdivision, Feeder, and DTR")
    public void selectSubdivisionFeederDTR() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ScrollView/android.widget.Spinner[1]"))).click();
            driver.findElement(By.xpath("//android.widget.TextView[@text='BANMALIPUR ESD-II']")).click();

            driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.Spinner[1]")).click();
            driver.findElement(By.xpath("//android.widget.TextView[@text='Central']")).click();

            driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.Spinner[2]")).click();
            driver.findElement(By.xpath("//android.widget.TextView[@text='Ujjayanta Market']")).click();
        	
        	
            Thread.sleep(1000);

            test.pass("Selected Subdivision, Feeder, and DTR");
        } catch (Exception e) {
            test.fail("Failed to select Subdivision/Feeder/DTR: " + e.getMessage());
        }
    }

    @And("Admin randomly selects Power On status")
    public void randomPowerStatusSelection() {
        try {
            scrollUp(driver); // Ensure dropdown is in view

            WebElement powerDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.ScrollView/android.widget.Spinner[3]")));
            powerDropdown.click();

            Thread.sleep(1000); // Let the dropdown open

            String[] options = {"Yes", "No"};
            String status = options[new Random().nextInt(options.length)];
            test.info("Randomly selected Power On status: " + status);

            WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='" + status + "']")));
            statusOption.click();

            // Scroll again if needed
            scrollUp(driver);

            // Fill the form accordingly
            if (status.equalsIgnoreCase("Yes")) {
                fillFormForPowerOnYes();
            } else {
                fillFormForPowerOnNo();
            }

        } catch (Exception e) {
            test.fail("Failed to randomly select Power On status: " + e.getMessage());
        }
    }

    @Then("Form is filled based on Power On status")
    public void formFilledMessage() {
        test.pass("Form was filled successfully based on randomly selected Power On status.");
    }

    public void fillFormForPowerOnYes() {
        try {
            // Step 1: Upload QR image of DCU
            WebElement qrScan = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//android.widget.TextView[@text='Click Now'])[1]")));
            qrScan.click();
            takePhotoFromCamera();

            // ✅ Wait a bit or re-stabilize the screen before next action
            Thread.sleep(1000);  // Allow UI to refresh

            // Step 2: Re-locate the next "Click Now" button for DCU with GPS
            List<WebElement> clickNowButtons = driver.findElements(By.xpath("//android.widget.TextView[@text='Click Now']"));

            if (clickNowButtons.size() >= 1) {
                WebElement dcuPhoto = clickNowButtons.get(clickNowButtons.size() - 1); // get last one
                dcuPhoto.click();
                takePhotoFromCamera();
            } else {
                test.fail("Second 'Click Now' button not found.");
                return;
            }

            // ✅ Scroll up to bring Submit button
            scrollUp(driver);

            // Step 3: Submit
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Submit']")));
            submitBtn.click();

            test.pass("Form filled and submitted successfully for Power On: YES");

        } catch (Exception e) {
            test.fail("Failed to fill form for Power On YES: " + e.getMessage());
        }
    }

    public void fillFormForPowerOnNo() {
        try {
            // Step 1: Upload Faulty DCU QR image
            WebElement faultyQR = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//android.widget.TextView[@text='Click Now'])[1]")));
            faultyQR.click();
            takePhotoFromCamera();
            Thread.sleep(1000); // Let UI refresh

            // Step 2: Upload Replaced DCU QR image (new layout, index resets)
            WebElement replacedQR = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//android.widget.TextView[@text='Click Now'])[1]")));
            replacedQR.click();
            takePhotoFromCamera();
            Thread.sleep(1000); // Let UI refresh again

            // Step 3: Upload DCU Photo with GPS tagging
            // Re-locate all "Click Now" buttons again after screen reload
            List<WebElement> clickNowButtons = driver.findElements(
                By.xpath("//android.widget.TextView[@text='Click Now']"));

            if (clickNowButtons.size() >= 1) {
                WebElement gpsPhotoClickNow = clickNowButtons.get(clickNowButtons.size() - 1); // Last one assumed to be GPS
               // scrollToElement(gpsPhotoClickNow);  // Scroll if offscreen
                gpsPhotoClickNow.click();
                takePhotoFromCamera();
            } else {
                test.fail("GPS tagging 'Click Now' button not found.");
                return;
            }

            Thread.sleep(1000);

            // Step 4: Scroll up and submit
            scrollUp(driver);

            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='Submit']")));
            submitBtn.click();

            test.pass("Form filled and submitted successfully for Power On: NO");

        } catch (Exception e) {
            test.fail("Failed to fill form for Power On NO: " + e.getMessage());
        }
    }


    public void scrollUp(AndroidDriver driver) {
        try {
            int height = driver.manage().window().getSize().height;
            int width = driver.manage().window().getSize().width;

            int startX = width / 2;
            int startY = (int) (height * 0.8);  // Start lower
            int endY = (int) (height * 0.3);    // Move upward

            new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();

            test.pass("Scrolled up successfully.");
        } catch (Exception e) {
            test.fail("Scroll up failed: " + e.getMessage());
        }
    }

    public void takePhotoFromCamera() {
        try {
//            WebElement shutter = driver.findElement(By.id("com.android.camera:id/shutter_button"));
//            shutter.click();
//            Thread.sleep(1500); 

            WebElement doneBtn = driver.findElement(By.xpath("//android.widget.TextView[@text='Capture']"));
            doneBtn.click();
        } catch (Exception e) {
            test.info("⚠️ Camera interaction skipped or failed.");
        }
    }
}

