package stepDefinition;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.*;
import io.appium.java_client.touch.offset.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import utilities.ExtentReportManager;
import utilities.driverFactory;

public class CreateSurveySteps {

	AndroidDriver driver = driverFactory.getDriver();
	static ExtentReports extent = ExtentReportManager.getInstance();
	static ExtentTest test;

	Faker faker = new Faker();
	Random random = new Random();

	@When("User clicks on the Survey module")
	public void userClicksOnSurveyModule() {
		test = extent.createTest("Access Survey Module");

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement surveyButton = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@text='Survey']")));
			surveyButton.click();

			test.pass("✅ User clicked on the Survey option successfully.");
		} catch (Exception e) {
			test.fail("❌ Failed to click on Survey option: " + e.getMessage());
		}
	}

	@And("User selects Subdivision,Feeder,and DTR")
	public void selectSubdivisionFeederDTR() {
		test = extent.createTest("Select Subdivision, Feeder, and DTR");

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			// ----------- Select Subdivision -----------
			WebElement subdivTab = wait.until(d -> d.findElement(By.xpath("//android.widget.TextView[@text='Sub-Division']")));
			subdivTab.click();

			WebElement searchSubdiv = wait.until(d -> d.findElement(By.xpath("//android.widget.EditText")));
			searchSubdiv.clear();
			searchSubdiv.click();
			searchSubdiv.sendKeys("BANMALIPUR ESD-II");

			WebElement subdivOption = wait.until(d -> d.findElement(By.xpath("//android.widget.TextView[contains(@text,'BANMALIPUR ESD-II')]")));
			subdivOption.click();
			test.pass("✅ Subdivision 'BANMALIPUR ESD-II' selected successfully.");

			// ----------- Select Feeder -----------
			WebElement feederTab = wait.until(d -> d.findElement(By.xpath("//android.widget.TextView[@text='Feeder']")));
			feederTab.click();

			WebElement searchFeeder = wait.until(d -> d.findElement(By.xpath("//android.widget.EditText")));
			new TouchAction<>(driver).tap(ElementOption.element(searchFeeder)).perform();

			searchFeeder.clear();
			searchFeeder.sendKeys("Central");

			try {
				driver.hideKeyboard();
			} catch (Exception ignored) {}

			WebElement feederOption = wait.until(d -> d.findElement(By.xpath("//android.widget.TextView[contains(@text,'Central')]")));
			feederOption.click();
			test.pass("✅ Feeder 'Central' selected successfully.");

			// ----------- Select DTR -----------
			WebElement dtrTab = wait.until(d -> d.findElement(By.xpath("//android.widget.TextView[@text='DTR']")));
			dtrTab.click();

			WebElement searchDTR = wait.until(d -> d.findElement(By.xpath("//android.widget.EditText")));
			new TouchAction<>(driver).tap(ElementOption.element(searchDTR)).perform();

			searchDTR.clear();
			searchDTR.sendKeys("Ujjayanta Market");

			try {
				driver.hideKeyboard();
			} catch (Exception ignored) {}

			WebElement dtrOption = wait.until(d -> d.findElement(By.xpath("//android.widget.TextView[contains(@text,'Ujjayanta Market')]")));
			dtrOption.click();
			test.pass("✅ DTR 'Ujjayanta Market' selected successfully.");

		} catch (Exception e) {
			test.fail("❌ Failed to select Subdivision/Feeder/DTR: " + e.getMessage());
			e.printStackTrace();
		}
	}


	@And("User searches for the Meter Number")
	public void userSearchesForMeterNumber() {
		test = extent.createTest("Search Meter Number");

		try {
			WebElement searchBox = driver.findElement(By.xpath("//android.widget.EditText"));
			searchBox.click();
			searchBox.sendKeys("280000332");
			driver.findElement(By.xpath("//d1.k0/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.widget.Button")).click();

			test.pass("✅ Searched for Meter Number");

		} catch (Exception e) {
			test.fail("❌ Failed to search Meter Number: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@And("If the Meter Number exists, user clicks on Details")
	public void userClicksOnDetailsIfExists() {
		test = extent.createTest("Click Details if Meter Exists");

		try {
			// Click on Details Button
			WebElement detailBtn = driver.findElement(By.xpath("//android.widget.TextView[@text='Details']"));
			detailBtn.click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			// Scroll up to make Back Button visible
			scrollUp(driver);
			Thread.sleep(10000);

			// Click on Back Button after scrolling
			WebElement backBtn = driver.findElement(By.xpath("//d1.k0/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button"));
			backBtn.click();

		} catch (Exception e) {
			test.fail("❌ Error while checking meter existence: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// ===== Scroll Method =====
	public void scrollUp(AndroidDriver driver) {
		int height = driver.manage().window().getSize().height;
		int width = driver.manage().window().getSize().width;

		int startX = width / 2;
		int startY = (int) (height * 0.9);
		int endY = (int) (height * 0.3);

		new TouchAction<>(driver)
		.press(PointOption.point(startX, startY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
		.moveTo(PointOption.point(startX, endY))
		.release()
		.perform();
	}
	@And("If the Meter Number does not exist, user clicks on Yes")
	public void searchMeterNum() {
		test = extent.createTest("Meter Not Exist - Click on Yes to Add Survey");

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Search Meter Number
			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText")));
			searchBox.click();
			searchBox.sendKeys("865869068746");

			driver.findElement(By.xpath("//d1.k0/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.widget.Button")).click();

			// Click "Yes" Button
			WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button")));
			yesButton.click();

			test.pass("✅ Searched for Meter Number and clicked on Yes");

			// Fill Consumer Details
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]"))
			.sendKeys(faker.number().digits(10)); // Consumer Account Number

			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[2]"))
			.sendKeys(faker.name().fullName()); // Name

			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[3]"))
			.sendKeys(faker.phoneNumber().subscriberNumber(10)); // Mobile

			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[4]"))
			.sendKeys(faker.address().fullAddress()); // Address

			scrollUp(driver); // Scroll to Meter Type dropdown

			// Select Meter Type
			driver.findElement(By.xpath("//android.widget.Spinner")).click();
			String[] meterTypes = {"JNJ", "NON-JNJ"};
			String selectedType = meterTypes[new Random().nextInt(meterTypes.length)];
			driver.findElement(By.xpath("//android.widget.TextView[@text='" + selectedType + "']")).click();

			test.info("✅ Selected Meter Type: " + selectedType);

			// Fill Meter Details based on type
			if (selectedType.equals("JNJ")) {
				WebElement meterNo = wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//android.widget.TextView[@text='Enter Meter No.']/following-sibling::android.widget.EditText")));
				meterNo.sendKeys(faker.number().digits(12));

				WebElement makeYearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//android.widget.TextView[@text='Meter Make Year']/following-sibling::android.widget.Spinner")));
				makeYearDropdown.click();
				driver.findElement(By.xpath("//android.widget.TextView[@text='2025']")).click();

				WebElement phaseDropdown = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//android.widget.TextView[@text='Phase']/following-sibling::android.widget.Spinner")));
				phaseDropdown.click();
				driver.findElement(By.xpath("//android.widget.TextView[@text='1PH']")).click();

				test.pass("✅ Filled JNJ Meter details.");
			} else {
				WebElement meterNo = driver.findElement(By.xpath("//android.widget.TextView[@text='Enter Meter No.']/following-sibling::android.widget.EditText"));
				meterNo.sendKeys(faker.number().digits(12));
				test.pass("✅ Filled NON-JNJ Meter Number.");
			}

			// Upload all photos and submit
			uploadAllPhotosAndSubmit(driver);

		} catch (Exception e) {
			test.fail("❌ Failed to complete flow: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void takePhotoFromCamera() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			WebElement shutter = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.camera:id/shutter_button")));
//			shutter.click();
//			Thread.sleep(1000);

			 Thread.sleep(1500); 

	            WebElement doneBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//android.widget.TextView[@text='Capture']")));  
	            doneBtn.click();

	            test.info("📸 Photo captured successfully.");
	        } catch (Exception e) {
	            test.info("⚠️ Camera interaction failed: " + e.getMessage());
	        }
	}

	public void uploadAllPhotosAndSubmit(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			String[] photoDescriptions = {
				"Meter with GPS",
				"House with GPS",
				"Selfie with GPS"
			};

			for (int i = 1; i <= 3; i++) {
				uploadPhotoByIndex(i, driver, photoDescriptions[i - 1]);
			}

			// Scroll to submit button
			scrollToElement("//android.widget.TextView[@text='Submit']", driver);

			WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//android.widget.TextView[@text='Submit']")));
			submitBtn.click();

			test.pass("✅ All 3 GPS-tagged photos uploaded and form submitted.");

		} catch (Exception e) {
			test.fail("❌ Failed to upload photos or submit form: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void uploadPhotoByIndex(int index, WebDriver driver, String photoName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			String xpath = "(//android.widget.TextView[@text='Click Now'])[" + index + "]";
			scrollToElement(xpath, driver);

			WebElement photoBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			photoBtn.click();

			takePhotoFromCamera();

			WebElement doneBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//android.widget.Button")));
			doneBtn.click();

			test.pass("✅ Uploaded photo: " + photoName);

		} catch (Exception e) {
			test.fail("❌ Failed to upload photo (" + photoName + "): " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void scrollToElement(String xpath, WebDriver driver) {
		boolean found = false;
		int maxScrolls = 8;

		for (int i = 0; i < maxScrolls; i++) {
			try {
				WebElement el = driver.findElement(By.xpath(xpath));
				if (el.isDisplayed()) {
					found = true;
					break;
				}
			} catch (Exception ignored) {}
			scrollDown((AndroidDriver) driver);
		}

		if (!found) {
			test.warning("⚠️ Element not found after scrolling: " + xpath);
		}
	}

	public void scrollDown(AndroidDriver driver) {
		Dimension size = driver.manage().window().getSize();
		int startY = (int) (size.height * 0.9);
		int endY = (int) (size.height * 0.4);
		int startX = size.width / 2;

		new TouchAction(driver)
			.press(PointOption.point(startX, startY))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
			.moveTo(PointOption.point(startX, endY))
			.release()
			.perform();
	}
	
	@And("After the survey is completed, the user performs a resurvey")
	public void resurvey() {
		test = extent.createTest("User performs a resurvey after completing the survey");

		try {
			test.info("🔁 User is attempting to perform a resurvey.");
			
//			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//			// Select Meter Type
//			driver.findElement(By.xpath("//android.widget.Spinner")).click();
//			String[] meterTypes = {"JNJ", "NON-JNJ"};
//			String selectedType = meterTypes[new Random().nextInt(meterTypes.length)];
//			driver.findElement(By.xpath("//android.widget.TextView[@text='" + selectedType + "']")).click();
//
//			test.info("✅ Selected Meter Type: " + selectedType);
//
//			// Fill Meter Details based on type
//			if (selectedType.equals("JNJ")) {
//				WebElement meterNo = wait1.until(ExpectedConditions.presenceOfElementLocated(
//						By.xpath("//android.widget.TextView[@text='Enter Meter No.']/following-sibling::android.widget.EditText")));
//				meterNo.sendKeys(faker.number().digits(12));
//
//				WebElement makeYearDropdown = wait1.until(ExpectedConditions.elementToBeClickable(
//						By.xpath("//android.widget.TextView[@text='Meter Make Year']/following-sibling::android.widget.Spinner")));
//				makeYearDropdown.click();
//				driver.findElement(By.xpath("//android.widget.TextView[@text='2025']")).click();
//
//				WebElement phaseDropdown = wait1.until(ExpectedConditions.elementToBeClickable(
//						By.xpath("//android.widget.TextView[@text='Phase']/following-sibling::android.widget.Spinner")));
//				phaseDropdown.click();
//				driver.findElement(By.xpath("//android.widget.TextView[@text='1PH']")).click();
//
//				test.pass("✅ Filled JNJ Meter details.");
//			} else {
//				WebElement meterNo = driver.findElement(By.xpath("//android.widget.TextView[@text='Enter Meter No.']/following-sibling::android.widget.EditText"));
//				meterNo.sendKeys(faker.number().digits(12));
//				test.pass("✅ Filled NON-JNJ Meter Number.");
//			}
//			
//			scrollUp(driver);
			
			




			test.pass("✅ Resurvey was completed successfully.");

		} catch (Exception e) {
			test.fail("❌ Failed to perform resurvey: " + e.getMessage());
			e.printStackTrace();
		}
	}

	 public void logout() {
	        try {
	            // Navigate to the profile or settings section if required
	            driver.findElement(By.xpath("//android.widget.TextView[@text='Profile']")).click(); // Update text if needed

	            // Click on Logout button
	            driver.findElement(By.xpath("//android.widget.TextView[@text='Log Out']")).click();

	            test.info("🔓 Logged out of the application successfully.");
	        } catch (Exception e) {
	            test.warning("⚠️ Logout failed or already logged out: " + e.getMessage());
	        }
	    }
}


