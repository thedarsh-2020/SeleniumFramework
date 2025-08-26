package runner;

import io.cucumber.testng.CucumberOptions;
import tests.TestBase;

@CucumberOptions(features= "src/test/java/features/UserRegisterationUsingBDD.feature"
,glue = {"steps"}
,plugin = {"pretty", "html:target/cucumber-html-report"})
public class TestRunnerUsingBDD extends TestBase
{
}
