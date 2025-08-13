package stepDefinition;

import io.cucumber.java.en.Given;
import utilities.driverFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utilities.ExtentReportManager;

public class LaunchAppSteps {

	ExtentReports extent = ExtentReportManager.getInstance();
	ExtentTest test = ExtentReportManager.createTest("Mobile App launched in Virtual Devices");

	@Given("User Launches the Mobile Application")
	public void the_user_launches_the_mobile_application() {
		try {
			if (driverFactory.getDriver() != null) {
				test.pass("✅ App already launched via Hooks");
				System.out.println("✅ App already launched via Hooks");
			} else {
				test.fail("❌ Driver is not initialized. Check Hooks configuration.");
				System.out.println("❌ Driver is not initialized. Check Hooks configuration.");
			}
		} catch (Exception e) {
			test.fail("❌ Error during app validation: " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
}

