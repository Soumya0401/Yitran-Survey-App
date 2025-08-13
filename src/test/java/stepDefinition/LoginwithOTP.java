package stepDefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ExtentReportManager;
import utilities.driverFactory;

public class LoginwithOTP {

	AndroidDriver driver = driverFactory.getDriver(); // ✅ Use shared driver
	static ExtentReports extent = ExtentReportManager.getInstance();
	static ExtentTest test;

	@When("User Enters the Mobile Number")
	public void enterMobileNumber() {
		test = extent.createTest("Login with Mobile Number");

		try {
			driver.findElement(By.xpath("//android.widget.TextView[@text='Login with phone']")).click();
			Thread.sleep(1000);

			WebElement mobileField = driver.findElement(By.className("android.widget.EditText"));
			mobileField.click();
			mobileField.sendKeys("9905899259");
			System.out.println("✅ Mobile number entered successfully");
			test.pass("✅ Mobile number entered successfully");

		} catch (Exception e) {
			test.fail("❌ Failed to enter mobile number: " + e.getMessage());
			e.printStackTrace();
		} 
	}

	@And("User Clicks on Get OTP")
	public void clickGetOTP() {
		try {
			driver.findElement(By.xpath("//android.widget.Button")).click();
			System.out.println("✅ Get OTP button clicked");
			test.pass("✅ Get OTP button clicked");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("❌ Failed to click Get OTP");
		}
	}
	//	@When("User Waits for OTP and Clicks Resend if Not Received")
	//	public void user_waits_for_otp_and_clicks_resend_if_not_received() {
	//	    try {
	//	        test.info("⏳ Waiting for 60 seconds for OTP to arrive...");
	//	        Thread.sleep(60000); // Simulating wait
	//
	//	        // Locate the OTP field
	//	        WebElement otpField = driver.findElement(By.className("android.widget.EditText"));
	//	        String otpText = otpField.getText();
	//
	//	        if (otpText == null || otpText.isEmpty()) {
	//	            test.info("⚠️ OTP not received. Clicking Resend OTP button.");
	//
	//	            // Click Resend OTP
	//	            WebElement resendBtn = driver.findElement(By.xpath("//android.widget.TextView[@text='Resend OTP']"));
	//	            resendBtn.click();
	//
	//	            test.pass("✅ Resend OTP button clicked.");
	//	            Thread.sleep(3000); // Small wait for new OTP
	//	        } else {
	//	            test.pass("✅ OTP auto-filled: " + otpText);
	//	        }
	//
	//	    } catch (Exception e) {
	//	        test.fail("❌ Error while waiting/resending OTP: " + e.getMessage());
	//	        e.printStackTrace();
	//	    }
	//	}

	@And("User Enters the OTP")
	public void enterOTP() {

		try {
			// Tap the 'Enter OTP' field (if needed)
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.findElement(By.xpath("//android.widget.TextView[@text='Enter OTP']")).click();
			Thread.sleep(1000);

			// Enter OTP in EditText field
			WebElement enterOtp = driver.findElement(By.className("android.widget.EditText"));
			enterOtp.click();
			enterOtp.sendKeys("1234");

			System.out.println("✅ OTP entered successfully");
			test.pass("✅ OTP entered successfully");

		} catch (Exception e) {
			test.fail("❌ Failed to enter OTP: " + e.getMessage());
			e.printStackTrace();
		} 
	}

	@And("User clicks on Verify OTP Button")
	public void clickverifybtn() {
		try {
			driver.findElement(By.xpath("//d1.k0/android.view.View/android.view.View/android.view.View[3]/android.widget.Button")).click();
			System.out.println("✅ Get OTP button clicked");
			test.pass("✅ Clicked Verified Button");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("❌ Failed to click Verify Button");
		}
	}

	@Then("User should be navigated to the Home Page")
	public void validateHomePageNavigation() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Optional: print current activity
			String currentActivity = ((AndroidDriver) driver).currentActivity();
			System.out.println("🔍 Current Activity: " + currentActivity);
			WebElement homeTitle = driver.findElement(By.id("android:id/content")); 

			if (homeTitle.isDisplayed()) {
				System.out.println("✅ Navigated to Home Page");
				test.pass("✅ User successfully navigated to the Home Page after login.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("❌ Home page not displayed after login.");
			test.fail("❌ User not navigated to Home Page. Exception: " + e.getMessage());   
		}

	}
	
	}

