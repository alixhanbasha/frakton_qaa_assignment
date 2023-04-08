package starter;

import io.cucumber.java.AfterAll;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

import static net.serenitybdd.core.Serenity.getDriver;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        // To run all the automated tests from the 1st assignment -> @Consumer_Contact_Form and not @NotAutomated
        // To run all the automated tests from the 2nd assignment -> @Yavlena
        // To run all the automated scenarios -> @Automated
        tags = "@Automated",
        features = "src/test/resources/features"
)
public class CucumberTestSuite {}
