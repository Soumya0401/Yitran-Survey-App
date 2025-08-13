package runner;

import io.cucumber.testng.CucumberOptions; 
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources/features/InstallNIC.feature",
    glue = "stepDefinition",
    		plugin = {
    		        "pretty",
    		        "html:target/CreateSurvey.html",
    		        "json:target/CreateSurvey.json",
    		        "junit:target/CreateSurvey.xml"
    		    },
    monochrome = true
)
public class InstallNICRunner  extends AbstractTestNGCucumberTests {
    // No code needed inside
}
