package starter.stepdefinitions.yavlena;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.SerenityReports;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import starter.navigation.NavigateTo;
import starter.page.yavlena.YavlenaHomePage;

import java.util.ArrayList;
import java.util.List;

public class YavlenaHomePageStepDefinitions {

    private final YavlenaHomePage page = new YavlenaHomePage();

    @Given("{actor} is a user of Yavlena services")
    public void actorIsAUserOfYavlenaServices(Actor actor) {
        actor.attemptsTo(
                NavigateTo.theYavlenaBrokerPage(),
                Ensure.that(page.waitForPageToLoad()).isTrue()
        );
    }

    @When("{actor} tries to view all the brokers")
    public void actorTriesToViewAllBrokers(Actor actor) {
        actor.attemptsTo(
                Scroll.to(page.getLoadMoreBrokersButton()),
                Click.on(page.getLoadMoreBrokersButton()),
                Ensure.that(page.waitForPageToLoad()).isTrue()
        );
    }

    @When("{actor} searches for a specific broker that works for Yavlena")
    public void actorSearchesForSpecificBroker(Actor actor) {
        Serenity.setSessionVariable("searched_broker").to("Александър Андреев");
        actor.attemptsTo(
                Click.on(page.getSearchBox()),
                Enter.theValue(Serenity.sessionVariableCalled("searched_broker").toString()).into(page.getSearchBox())
        );
    }

    @When("{actor} searches every broker in the system")
    public void actorSearchesAllTheBrokersOneByOne(Actor actor) {
        actorTriesToViewAllBrokers(actor);
        actorCanSeeAllTheBrokers(actor);

        List<String> brokerNames = new ArrayList<>();
        page.getListOfBrokerNames().forEach(broker -> {
            page.waitFor(broker);
            brokerNames.add(broker.getText());
        });

        brokerNames.forEach(brokerName -> {
            Serenity.setSessionVariable("searched_broker").to(brokerName);
            actor.attemptsTo(
                    Enter.theValue(brokerName).into(page.getSearchBox())
            );
            actorSeesTheBrokerIsPresent(actor);
        });
    }

    @Then("{actor} is prompted with a list of all the available brokers")
    public void actorCanSeeAllTheBrokers(Actor actor) {
        actor.wasAbleTo(
                Ensure.that(page.waitForSomeTime(5000)).isTrue(),
                Ensure.that(page.getListOfBrokers().size()).isEqualTo(102)
        );
    }

    @Then("{actor} sees the broker is present in the system")
    public void actorSeesTheBrokerIsPresent(Actor actor) {
        page.waitForSomeTime(2000);
        actor.wasAbleTo(
                // Expect one broker
                Ensure.that(page.waitForVisibilityOfElements(page.getListOfBrokers())).isTrue(),
                Ensure.that(page.getListOfBrokers().size()).isEqualTo(1),

                // Make sure this is the broker we searched for
                Ensure.that(page.getBrokerName().getText()).isEqualTo(Serenity.sessionVariableCalled("searched_broker")),
                Ensure.that(page.waitForVisibilityOfElement(page.getBrokerOfficeAddress())).isTrue(),

                // Make sure the phone numbers are present
                Ensure.that(page.waitForVisibilityOfElements(page.getPhoneNumbers())).isTrue(),
                Ensure.that(page.getPhoneNumbers().size()).isGreaterThanOrEqualTo(1),

                // Make sure the properties of the broker are displayed
                Ensure.that(page.waitForVisibilityOfElement(page.getBrokerProperties())).isTrue()
        );
    }

}
