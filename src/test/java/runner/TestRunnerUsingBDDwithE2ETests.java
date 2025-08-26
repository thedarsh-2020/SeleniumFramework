package runner;

import io.cucumber.testng.CucumberOptions;
import tests.TestBase;

@CucumberOptions(features= "src/test/java/features/End2End_Tests.feature"
,glue = {"stepsWithE2ETests"}
,plugin = {"pretty", "html:target/cucumber-html-report"})
public class TestRunnerUsingBDDwithE2ETests extends TestBase
{
}
