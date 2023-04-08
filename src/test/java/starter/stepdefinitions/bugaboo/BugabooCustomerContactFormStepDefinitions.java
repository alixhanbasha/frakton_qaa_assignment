package starter.stepdefinitions.bugaboo;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import starter.navigation.NavigateTo;
import starter.page.bugaboo.BugabooCustomerContactForm;

import java.util.List;
import java.util.stream.Collectors;

import static starter.utils.Constants.WARNING_MESSAGE;

@Slf4j
public class BugabooCustomerContactFormStepDefinitions{

    private final BugabooCustomerContactForm customerContactForm = new BugabooCustomerContactForm();

    @When("{actor} wants to submit a customer contact form")
    public void actorWnatsToSubmitAForm(Actor actor) {
        actor.wasAbleTo(NavigateTo.theBugabooCustomerContactForm());
        customerContactForm.waitForVisibilityOfElement(customerContactForm.getPageHeading());

        Assertions.assertThat(customerContactForm.getPageHeading().getText())
                .isEqualTo("Customer Contact");
    }

    @And("{actor} chooses the {string} question")
    public void actorChoosesOneQuestion(Actor actor, String question) {
        actor.attemptsTo(
                Click.on(customerContactForm.getOpenQuestions())
        );

        // get the list of questions, and search what we are looking for
        // NOTE: as long as "question" variable is something present in the UI list, this will work.
        List<WebElement> questions = customerContactForm.getQuestionsBox()
                .findElements(By.cssSelector("lightning-base-combobox-item"))
                .stream()
                .filter(element -> element.getText().contains(question))
                .collect(Collectors.toList());

        questions.get(0).click();
    }

    @And("{actor} chooses one of the available options")
    public void actorChoosesOneOfTheAvailableOptions(Actor actor) {
        customerContactForm.waitForVisibilityOfElements(customerContactForm.getAnswersList());

        // just get the first answer for now.
        WebElementFacade answer = customerContactForm.getAnswersList().get(0);
        actor.attemptsTo(Click.on(answer));
        Serenity.setSessionVariable("answer").to(answer.getText());
    }

    @But("{actor} clicks \"Next\" without choosing a question")
    public void actorClicksNext(Actor actor) {
        actor.attemptsTo(
                Click.on(customerContactForm.getNextButton())
        );
    }

    @And("{actor} selects {string} question")
    public void actorSelectAQuestion(Actor actor, String question) {
        actorChoosesOneQuestion(actor, question);
        goNext();
    }

    @Then("{actor} should see a warning message")
    public void actorShouldSeeAWarningMessage(Actor actor) {
        WebElementFacade element = customerContactForm.getWarningMessage();
        customerContactForm.waitForVisibilityOfElement(element);

        Assertions.assertThat(element.getText())
                .isEqualTo(WARNING_MESSAGE);
    }

    @Then("{actor} should see the explanation")
    public void actorShouldSeeExplanation(Actor actor) {
        // a complicated xpath, but it ensures that the answer that is displayed, is
        // in fact the answer we are looking for. I could have simplified it just as
        // "//div[@aria-hidden='false']", but thought the extra step would give us more
        // confidence in the test
        actor.attemptsTo(
                Ensure.that(
                        By.xpath(
                                "//div[@interop-accordionsection_accordionsection]//*[contains(text(), '" +
                                Serenity.sessionVariableCalled("answer") + "')]/../../../../div[@aria-hidden='false']"
                        )
                ).isDisplayed()
        );
        return;
    }

    private void goNext() {
        customerContactForm.getNextButton().click();
    }

}
