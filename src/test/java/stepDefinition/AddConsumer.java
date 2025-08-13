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
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.en.And;
import utilities.ExtentReportManager;
import utilities.driverFactory;

public class AddConsumer {

	AndroidDriver driver = driverFactory.getDriver();
	static ExtentReports extent = ExtentReportManager.getInstance();
	static ExtentTest test;

	Faker faker = new Faker();
	Random random = new Random();

	@And("Surviour Add the Consumer")
	public void addConsumer() {
		test = extent.createTest("Surviour Adds the Consumer");

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			// Click "Add Consumer"
			driver.findElement(By.xpath("//android.widget.TextView[@text='Add Consumer']")).click();

			// Select Subdivision
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.Spinner[1]")).click();
			driver.findElement(By.xpath("//android.widget.TextView[@text='BANMALIPUR ESD-II']")).click();

			// Select Feeder
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.Spinner[1]")).click();
			driver.findElement(By.xpath("//android.widget.TextView[@text='MG Bazar']")).click();

			// Select DTR
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.Spinner[2]")).click();
			driver.findElement(By.xpath("//android.widget.TextView[@text='Julantha Bridge']")).click();

			// Consumer Account Number
			String accNumber = faker.number().digits(10);
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[2]")).sendKeys(accNumber);

			// Consumer Name
			String name = faker.name().fullName();
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[3]")).sendKeys(name);

			// Mobile Number
			String mobile = faker.phoneNumber().subscriberNumber(10);
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[4]")).sendKeys(mobile);

			// Address
			String address = faker.address().fullAddress();
			driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[5]")).sendKeys(address);


			// Scroll up to access meter fields
			scrollUp(driver);

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
			WebElement shutter = driver.findElement(By.id("com.android.camera:id/shutter_button"));
			shutter.click();
			Thread.sleep(1000);

			WebElement doneBtn = driver.findElement(By.id("com.android.camera:id/done_button"));
			doneBtn.click();
		} catch (Exception e) {
			test.info("⚠️ Camera interaction skipped or failed.");
		}
	}

	public void uploadAllPhotosAndSubmit(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			for (int i = 1; i <= 4; i++) {
				uploadPhotoByIndex(i, driver);
			}

			// Scroll to submit button
			scrollToElement("//android.widget.TextView[@text='Submit']", driver);

			WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@text='Submit']")));
			submitBtn.click();

			test.pass("✅ All 4 photos uploaded and form submitted.");
		} catch (Exception e) {
			test.fail("❌ Failed to upload all photos or submit: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void uploadPhotoByIndex(int index, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Scroll to the photo section
			String xpath = "(//android.widget.TextView[@text='Click Now'])[" + index + "]";
			scrollToElement(xpath, driver);

			WebElement photoBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			photoBtn.click();

			takePhotoFromCamera();

			WebElement doneBtn = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.Button")));
			doneBtn.click();

			//Thread.sleep(1000); // buffer between uploads
			test.pass("✅ Photo uploaded at index: " + index);
		} catch (Exception e) {
			test.fail("❌ Failed to upload photo at index " + index + ": " + e.getMessage());
			e.printStackTrace();
		}
	}


	public void scrollToElement(String xpath, WebDriver driver) {
		boolean found = false;
		int maxScrolls = 5;

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

	// ===== Scroll UP Method =====
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


	// ===== Scroll Down Method =====
	public void scrollDown(AndroidDriver driver) {
		Dimension size = driver.manage().window().getSize();
		int startY = (int) (size.height * 0.9);//0.7
		int endY = (int) (size.height * 0.4);//0.3
		int startX = size.width / 2;

		new TouchAction(driver)
		.press(PointOption.point(startX, startY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
		.moveTo(PointOption.point(startX, endY))
		.release()
		.perform();
	}

}
