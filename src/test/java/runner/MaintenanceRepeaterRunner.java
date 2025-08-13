package runner;

import io.cucumber.testng.CucumberOptions; 

import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources/features/MaintenanceRepeater.feature",
    glue = "stepDefinition",
    		plugin = {
    		        "pretty",
    		        "html:target/CreateSurvey.html",
    		        "json:target/CreateSurvey.json",
    		        "junit:target/CreateSurvey.xml"
    		    },
    monochrome = true
)
public class MaintenanceRepeaterRunner extends AbstractTestNGCucumberTests {
    // No code needed inside
}
