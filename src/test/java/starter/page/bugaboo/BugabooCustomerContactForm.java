package starter.page.bugaboo;

import lombok.Getter;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;
import starter.utils.AbstractPage;

import java.util.List;

@Getter
@DefaultUrl("https://service.bugaboo.com/s/consumer-contact?selectedItem=Consumer_Contact_Form__c&language=en_US")
public class BugabooCustomerContactForm extends AbstractPage {

    @FindBy(xpath = "//h2[@class='contentTitle']")
    WebElementFacade pageHeading;

    @FindBy(xpath = "//button/span[contains(text(), 'What is your question')]")
    WebElementFacade openQuestions;

    @FindBy(xpath = "//div[@lightning-basecombobox_basecombobox and @role='listbox']")
    WebElementFacade questionsBox;

    @FindBy(css = "lightning-base-combobox-item")
    List<WebElementFacade> questions;

    public static final Target QUESTIONS =  Target.the("questions").locatedBy("lightning-base-combobox-item");

    // "//lightning-base-combobox-item/span/span[contains(text(), 'Delivery, Return or Refund')]"

    @FindBy(xpath = "//button[@interop-accordionsection_accordionsection]")
    List<WebElementFacade> quickHelp;

    @FindBy(xpath = "//button[contains(text(), 'Next')]")
    WebElementFacade nextButton;

    @FindBy(id = "help-text-21")
    WebElementFacade helpMessage;

    @FindBy(xpath = "//span[contains(text(), 'Please choose a question.')]")
    WebElementFacade warningMessage;

    @FindBy(xpath = "//div[@interop-accordionsection_accordionsection and @aria-hidden='false']")
    WebElementFacade explanation;

    @FindBy(xpath = "//button[@interop-accordionsection_accordionsection]")
    List<WebElementFacade> answersList;
}
