package stepDefinition;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import utilities.ExtentReportManager;
import utilities.driverFactory;

public class NICInstallation {
	AndroidDriver driver = driverFactory.getDriver();
	static ExtentReports extent = ExtentReportManager.getInstance();
	static ExtentTest test;
	Faker faker = new Faker();
	Random random = new Random();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	@When("Admin selects the Installation module")
	public void adminClicksInstallation() {
		try {
			test = extent.createTest("NIC Installation - Access Module");
			WebElement installModule = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@text='Installation']")));
			installModule.click();
			test.pass("Clicked on Installation module successfully");
		} catch (Exception e) {
			test.fail("Failed to click Installation module: " + e.getMessage());
		}
	}

	@And("Admin selects NIC")
	public void adminSelectsNIC() {
		try {
			WebElement nicOption = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@text='NIC']")));
			nicOption.click();
			test.pass("Selected NIC successfully");
		} catch (Exception e) {
			test.fail("Failed to select NIC: " + e.getMessage());
		}
	}

	@And("Admin selects Subdivision, Feeder, and DTR for NIC device")
	public void dtrfeederSubdiv() {
		test = extent.createTest("Select Subdivision, Feeder, and DTR");
		try {
			selectDropdown("Sub-Division", "BANMALIPUR ESD-II");
			selectDropdown("Feeder", "Central");
			selectDropdown("DTR", "Ujjayanta Market");
			test.pass("✅ Subdivision, Feeder, and DTR selected successfully.");
		} catch (Exception e) {
			test.fail("❌ Failed to select Subdivision/Feeder/DTR: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void selectDropdown(String label, String value) {
		WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//android.widget.TextView[@text='" + label + "']")));
		tab.click();
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText")));
		new TouchAction<>(driver).tap(ElementOption.element(input)).perform();
		input.clear();
		input.sendKeys(value);
		try { driver.hideKeyboard(); } catch (Exception ignored) {}
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//android.widget.TextView[contains(@text,'" + value + "')]")));
		option.click();
	}

	@And("Admin enters the Meter Number")
	public void enterMeterNumber() {
		test = extent.createTest("Search Meter Number");
		try {
			WebElement meterInput = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.EditText")));
			meterInput.click();
			meterInput.sendKeys("857646464616");
			WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@text='Search']")));
			searchBtn.click();
			test.pass("✅ Meter number entered and search initiated.");
		} catch (Exception e) {
			test.fail("❌ Failed to enter meter number: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@And("If the Meter Number exists, admin clicks on Details")
	public void clickDetailsIfExists() {
		test = extent.createTest("Check Meter Existence");
		try {
			WebElement detailBtn = driver.findElement(By.xpath("//android.widget.TextView[@text='Details']"));
			detailBtn.click();
			scrollUp(driver);
			WebElement backBtn = driver.findElement(By.xpath("//android.widget.Button"));
			backBtn.click();
		} catch (Exception e) {
			test.fail("❌ Error while checking meter existence: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@And("If the Meter Number is not found in NIC, admin clicks on Yes")
	public void missingMeterNum() {
		test = extent.createTest("Meter Not Exist - Click on Yes to Add Survey");
		try {
			// Search and Yes flow
			WebElement meterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText")));
			meterInput.click();
			meterInput.sendKeys("875793231245");
			WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@text='Search']")));
			searchBtn.click();
			WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@text='Yes']")));
			yesButton.click();

			// Fill details
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]"))
				.sendKeys(faker.number().digits(15));
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[2]"))
				.sendKeys(faker.name().fullName());
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[3]"))
				.sendKeys(faker.phoneNumber().subscriberNumber(10));
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[4]"))
				.sendKeys(faker.address().fullAddress());

			scrollUp(driver);

			WebElement meterNo = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@text='Enter Meter No.']/following-sibling::android.widget.EditText")));
			meterNo.click();
			meterNo.sendKeys(faker.number().digits(12));
			test.pass("✅ Filled Meter Number.");

			selectSpinner("Change", "2024");
			selectSpinner("Change", "1PH");

			WebElement nicDropdown = driver.findElement(
					By.xpath("//android.widget.ScrollView/android.widget.Spinner[2]"));
			nicDropdown.click();
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@text='Installed']"))).click();
			
			   scrollUp(driver);
		} catch (Exception e) {
			test.fail("❌ Failed to fill form for new meter: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void selectSpinner(String changeText, String selectionText) {
		WebElement change = driver.findElement(By.xpath("//android.widget.TextView[@text='" + changeText + "']"));
		change.click();
		driver.findElement(By.xpath("//android.widget.Spinner")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + selectionText + "']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Confirm']")).click();
		test.pass("✅ Filled " + changeText + " with " + selectionText);
		
	}
	
	public void NICInstallationdetails() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	    try {
	        // Capture photo of the meter
//	        WebElement meterPhotoBtn = wait.until(ExpectedConditions.elementToBeClickable(
//	            By.xpath("(//android.widget.TextView[@text='Click Now'])[1]")));
	        WebElement meterBtn = wait.until(ExpectedConditions.elementToBeClickable(
	        	    By.xpath("//android.widget.TextView[@text='Photo of Meter *']/following-sibling::android.view.View//android.widget.TextView[@text='Click Now']")));
	        meterBtn.click();
	        takePhotoFromCamera(driver);
	        Thread.sleep(1500); // Optional wait

	        // Capture photo of the electricity bill
	        WebElement billPhotoBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("(//android.widget.TextView[@text='Click Now'])[2]")));
	        billPhotoBtn.click();
	        takePhotoFromCamera(driver);
	        Thread.sleep(1500); // Optional wait

	        // Capture photo of the house
	        WebElement housePhotoBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("(//android.widget.TextView[@text='Click Now'])[3]")));
	        housePhotoBtn.click();
	        takePhotoFromCamera(driver);
	        Thread.sleep(1000); // Optional wait

	        ScrollUp(driver); // Scroll to submit button

	        // Submit the form
	        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//android.widget.TextView[@text='Submit']")));
	        submitBtn.click();

	        test.pass("📸 All GPS-tagged images captured and form submitted successfully.");

	    } catch (Exception e) {
	        test.fail("❌ Failed to submit form with GPS-tagged images: " + e.getMessage());
	    }
	}

	// Camera interaction method
	public void takePhotoFromCamera(WebDriver driver) {
	    try {
	        Thread.sleep(1500); // Allow camera to open

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement captureBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//android.widget.TextView[@text='Capture']")));
	        captureBtn.click();

	        test.info("📸 Photo captured successfully.");
	    } catch (Exception e) {
	        test.info("⚠️ Camera interaction failed: " + e.getMessage());
	    }
	}


	public void scrollUp(AndroidDriver driver) {
		Dimension size = driver.manage().window().getSize();
		int startX = size.width / 2;
		int startY = (int) (size.height * 0.9);
		int endY = (int) (size.height * 0.3);
		new TouchAction<>(driver).press(PointOption.point(startX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
				.moveTo(PointOption.point(startX, endY)).release().perform();
	}

	public void ScrollUp(WebDriver driver) {
		scrollUp((AndroidDriver) driver);
	}

	public void scrollToElement(String xpath, WebDriver driver) {
		boolean found = false;
		for (int i = 0; i < 5; i++) {
			try {
				WebElement el = driver.findElement(By.xpath(xpath));
				if (el.isDisplayed()) {
					found = true;
					break;
				}
			} catch (Exception ignored) {}
			scrollDown((AndroidDriver) driver);
		}
		if (!found) test.warning("⚠️ Element not found after scrolling: " + xpath);
	}

	public void scrollDown(AndroidDriver driver) {
		Dimension size = driver.manage().window().getSize();
		int startX = size.width / 2;
		int startY = (int) (size.height * 0.9);
		int endY = (int) (size.height * 0.4);
		new TouchAction<>(driver).press(PointOption.point(startX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
				.moveTo(PointOption.point(startX, endY)).release().perform();
	}
}

