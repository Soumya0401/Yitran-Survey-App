package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class BaseTestRunner {

	public static AndroidDriver driver;

	public void initializeDriver() {
		try {
			String apkPath = "E:\\Yitran_App\\yitran\\yitran_app\\resources\\yitran3006.apk";
			File appFile = new File(apkPath);

			if (!appFile.exists()) {
				System.out.println("❌ APK not found at: " + apkPath);
				return;
			}

			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName("Yitran_Phone");
			options.setPlatformName("Android");
			options.setAutomationName("UiAutomator2");
			options.setApp(appFile.getAbsolutePath());
			options.setNoReset(true); // Prevents app from reinstalling
			options.setAutoGrantPermissions(true);

			URL serverURL = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver(serverURL, options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.out.println("✅ Mobile App launched");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("❌ Error initializing driver: " + e.getMessage());
		}
	}
}
