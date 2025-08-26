package runner;

import io.cucumber.testng.CucumberOptions;
import tests.TestBase;

@CucumberOptions(features= "src/test/java/features/UserRegisterationUsingBDDwithDDT.feature"
,glue = {"stepsWithDDT"}
,plugin = {"pretty", "html:target/cucumber-html-report"})
public class TestRunnerUsingBDDwithDDT extends TestBase
{
}
