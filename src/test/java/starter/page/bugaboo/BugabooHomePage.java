package starter.page.bugaboo;

import lombok.Getter;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;
import starter.utils.AbstractPage;

@DefaultUrl("https://service.bugaboo.com/s/?language=en_US")
@Getter
public class BugabooHomePage extends AbstractPage {
    @FindBy(xpath = "//h2[@class='contentTitle']")
    WebElementFacade pageHeading;

    @FindBy(xpath = "//button[contains(text(), 'Accept all cookies')]")
    WebElementFacade acceptAllCookies;
}
