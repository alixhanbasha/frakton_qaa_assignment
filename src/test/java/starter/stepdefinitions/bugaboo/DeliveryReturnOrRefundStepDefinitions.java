package starter.stepdefinitions.bugaboo;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import org.assertj.core.api.Assertions;
import starter.page.bugaboo.DeliveryReturnOrRefund;

import java.util.stream.Collectors;

import static starter.utils.Constants.*;

public class DeliveryReturnOrRefundStepDefinitions{

    private final DeliveryReturnOrRefund page = new DeliveryReturnOrRefund();

    @And("{actor} does not fill out the form properly")
    public void actorDoesNotFillOutTheFormProperly(Actor actor) {
        actor.attemptsTo(
                Enter.theValue("B1234").into(page.getOrderNumber()),
                Enter.theValue("123123").into(page.getItemCode()),
                Enter.theValue("This is some dummy data").into(page.getDescription()),
                Enter.theValue("lastname").into(page.getLastName()),
                Enter.theValue(actor.getName() + "@testmail.com").into(page.getEmail()),
                Enter.theValue(actor.getName() + "@testmail.com").into(page.getVerityEmail()),
                Scroll.to(page.getCountryFlag()),
                Click.on(page.getCountryFlag()),
                Click.on(page.getKosovoCountryFlag()),
                Click.on(page.getPhoneNumber())
        );
    }

    @But("{actor} enters an invalid email address")
    public void actorEntersInvalidEmailAddress(Actor actor) {
        actor.attemptsTo(
                Enter.theValue("!@#$$%^&*()").into(page.getEmail()),
                Enter.theValue("!@#$$%^&*()").into(page.getVerityEmail()),
                Click.on(page.getSubmitButton())
        );
    }

    @But("{actor} enters an email address that does not match")
    public void actorEntersMismatchingEMailAdresses(Actor actor) {
        actor.attemptsTo(
                Enter.theValue("test@email.com").into(page.getEmail()),
                Enter.theValue("!@#$$%^&*()").into(page.getVerityEmail()),
                Click.on(page.getSubmitButton())
        );
    }

    @And("{actor} submits incorrect form")
    public void actorSubmitsIncorrectForm(Actor actor) {
        actorDoesNotFillOutTheFormProperly(actor);
        page.getSubmitButton().click();
    }

    @Then("{actor} should see an error message about {string}")
    public void actorShouldSeeAnErrorMessage(Actor actor, String context) throws InterruptedException {
        Assertions.assertThat(page.getErrorMessages().size())
                .isGreaterThan(0);

        switch (context.toLowerCase()) {
            case "missing input":
                assertErrorIsDisplayed(INPUT_NOT_OPTIONAL);
                break;

            case "missing captcha":
                assertErrorIsDisplayed(MISSING_CAPTCHA);
                break;

            case "invalid email":
                assertErrorIsDisplayed(INVALID_EMAIL);
                break;

            case "emails not matching":
                assertErrorIsDisplayed(MISMATCHING_EMAILS);
                break;

            default:
                throw new IllegalArgumentException("Not an argument I inderstand.");
        }
    }

    private void assertErrorIsDisplayed(String errorMessage) {
        WebElementFacade error = page.getErrorMessages().stream()
                .filter(element -> element.getText().contains(errorMessage))
                .collect(Collectors.toList())
                .get(0);

        Assertions.assertThat(page.getErrorMessages().get(0).isVisible())
                .isTrue();
    }

}
