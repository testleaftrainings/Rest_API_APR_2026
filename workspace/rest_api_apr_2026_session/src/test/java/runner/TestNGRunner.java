package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		         features = {"src/test/java/features/refactored-incident-service.feature:30"},
		         glue = {"step.defs"},
		         dryRun = false,
		         plugin = {
		        		 "pretty",
		        		 "html:cucumber-report/result.html"
		         }
		        )
public class TestNGRunner extends AbstractTestNGCucumberTests {

}