package testRunners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
         features = {"src/test/resources/Features"},
         glue = {"steps"},
         plugin = {"pretty", "html:tar" +
                 "get/site/cucumber-pretty.html",
                 "json:target/cucumber.json",
                 "junit:target/JunitReports/report.xml"},
                  tags="@smokeTest"
)
public class RunTests {
}
