package starter.stepdefinitions.bugaboo;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import starter.navigation.NavigateTo;
import starter.page.bugaboo.BugabooHomePage;

@Slf4j
public class BugabooHomePageStepDefinitions{

    private final BugabooHomePage homePage = new BugabooHomePage();

    @Given("{actor} is a customer of Bugaboo")
    public void actorIsACustomerOfBugaboo(Actor actor) {
        actor.wasAbleTo(
                NavigateTo.theBugabooHomePage(),
                Click.on(homePage.getAcceptAllCookies()),
                Ensure.that(homePage.waitForPageToLoad()).isTrue()
        );
    }

}
