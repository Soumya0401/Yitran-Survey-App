package stepDefinition;

import base.BaseTestRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utilities.ExtentReportManager;
import utilities.driverFactory;

public class Hooks {

    ExtentReports extent;
    ExtentTest test;
    BaseTestRunner base = new BaseTestRunner();

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        test = extent.createTest("Yitan APP invoked Sucessfully");
        base.initializeDriver();
        driverFactory.setDriver(BaseTestRunner.driver);
        test.info("Yitran App Launched");
    }

    
   

    @After
    public void tearDown() {
        if (BaseTestRunner.driver != null) {
            BaseTestRunner.driver.quit();
            test.info("Driver closed");
        }
        extent.flush(); // Write report
    }
}

