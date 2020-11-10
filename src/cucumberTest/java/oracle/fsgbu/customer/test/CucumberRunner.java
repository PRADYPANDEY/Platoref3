package oracle.fsgbu.customer.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/cucumberTest/resources/features"},
//        plugin = {"pretty","json:build/cucumber.json","junit:build/cucumber.xml" },
//        glue = {"com.oracle.fsgbu.plato.customer.steps"},
        format = { "pretty", "html:build/test-results/cucumberTest/html",
		"json:build/test-results/cucumber/cucumberTest/report.json" },
        monochrome = true
)
public class CucumberRunner {

}
