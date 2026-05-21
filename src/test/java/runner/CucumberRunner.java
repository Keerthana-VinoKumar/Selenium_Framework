package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		    features = "src/test/resources/features",
		    glue = {"stepdefinitions", "hooks"},
		    plugin = {
		        "pretty",
		        "html:target/cucumber-reports/ui-report.html",
		        "json:target/cucumber-reports/ui-report.json"
		    },
		    monochrome = true,
		    tags = "@smoke"          // run smoke by default
		)
		public class CucumberRunner
		    extends AbstractTestNGCucumberTests {}


