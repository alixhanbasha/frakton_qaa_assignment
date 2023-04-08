package starter.page.yavlena;

import lombok.Getter;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;
import starter.utils.AbstractPage;

import java.util.List;

@Getter
@DefaultUrl("https://www.yavlena.com/broker/")
public class YavlenaHomePage extends AbstractPage {

    @FindBy(css = "h2.brokers-section-title")
    WebElementFacade pageHeading;

    @FindBy(xpath = "//input[@data-container='broker-keyword']")
    WebElementFacade searchBox;

    @FindBy(xpath = "//a[@data-container='load-more-brokers']")
    WebElementFacade loadMoreBrokersButton;

    @FindBy(css = "article.broker-card")
    List<WebElementFacade> listOfBrokers;

    @FindBy(xpath = "//div[@class='broker-list']/div/span[contains(text(), 'Няма намерени резултати. Моля, разширете критериите на търсенето!')]")
    WebElementFacade emptyBrokerList;

    @FindBy(xpath = "//h3[@class='name']/a")
    List<WebElementFacade> listOfBrokerNames;

    @FindBy(xpath = "//h3[@class='name']/a")
    WebElementFacade brokerName;

    @FindBy(css = "div.office")
    WebElementFacade brokerOfficeAddress;

    @FindBy(xpath = "//span[@class='tel']")
    List<WebElementFacade> phoneNumbers;

    @FindBy(xpath = "//div[@class='position']/a")
    WebElementFacade brokerProperties;

}
