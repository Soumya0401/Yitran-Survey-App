package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    features = "src/test/resources/features/CreateSurvey.feature",
    glue = "stepDefinition",
    plugin = {
        "pretty",
        "html:target/CreateSurveyReport.html",
        "json:target/CreateSurvey.json"
    },
    monochrome = true
)
public class CreateSurveyRunner extends AbstractTestNGCucumberTests {
    
}
