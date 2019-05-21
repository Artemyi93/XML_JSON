package cucumber.tests;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.java.ru.*;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(//
    features = "src/test/resources/features",//
    glue = "",//
    dryRun = false,//
    strict = true,//
    snippets= SnippetType.UNDERSCORE )

public class CucumberRunnerTest {

}
