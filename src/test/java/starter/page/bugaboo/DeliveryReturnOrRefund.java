package starter.page.bugaboo;

import lombok.Getter;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import starter.utils.AbstractPage;

import java.util.List;

@Getter
public class DeliveryReturnOrRefund extends AbstractPage {

    @FindBy(xpath = "//input[@name='Order_Number']")
    WebElementFacade orderNumber;


    @FindBy(xpath = "//input[@name='Item_Code']")
    WebElementFacade itemCode;

    @FindBy(xpath = "//textarea[@lightning-textarea_textarea]")
    WebElementFacade description;


    @FindBy(xpath = "//input[@name='First_Name']")
    WebElementFacade firstName;

    @FindBy(xpath = "//input[@name='Last_Name']")
    WebElementFacade lastName;

    @FindBy(xpath = "//input[@name='Email']")
    WebElementFacade email;

    @FindBy(xpath = "//input[@name='Verify_EMail']")
    WebElementFacade verityEmail;

    @FindBy(css = "div[class='iti__selected-flag']")
    WebElementFacade countryFlag;

    @FindBy(xpath = "//div[@class='iti__flag-container']/ul/li/span[contains(text(), 'Kosovo')]")
    WebElementFacade kosovoCountryFlag;

    @FindBy(xpath = "//input[@name='country' and @type='tel']")
    WebElementFacade phoneNumber;


    @FindBy(xpath = "//select[@name='country']")
    WebElementFacade countryList;

    @FindBy(xpath = "//div[@class='flowruntime-input-error slds-form-element__help']/lightning-formatted-rich-text/span")
    List<WebElementFacade> errorMessages;

    @FindBy(xpath = "//span[@id='recaptcha-anchor']")
    WebElementFacade captcha;

    @FindBy(xpath = "//button[contains(text(), 'Submit')]")
    WebElementFacade submitButton;

}
