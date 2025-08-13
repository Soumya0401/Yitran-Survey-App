package stepDefinition;

import base.baseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utilities.ExtentReportManager;
import utilities.driverFactory;

public class Hooks {

    ExtentReports extent;
    ExtentTest test;
    baseTest base = new baseTest();

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        test = extent.createTest("Yitan APP invoked Sucessfully");
        base.initializeDriver();
        driverFactory.setDriver(baseTest.driver);
        test.info("Yitran App Launched");
    }

    
   

    @After
    public void tearDown() {
        if (baseTest.driver != null) {
            baseTest.driver.quit();
            test.info("Driver closed");
        }
        extent.flush(); // Write report
    }
}

