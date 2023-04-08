package starter.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.webdriver.javascript.JavascriptExecutorFacade;
import org.jetbrains.annotations.NotNull;
import java.time.Duration;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Getter
/*
* Helper class for that implements some useful methods
* */
public abstract class AbstractPage extends PageObject {

    protected final int DEFAULT_WAIT_DURATION_FOR_ELEMENTS = 30; // seconds
    private final JavascriptExecutorFacade jsExecutor = new JavascriptExecutorFacade(getDriver());

    public Boolean waitForPageToLoad(){
        return new WebDriverWait(
                getDriver(), Duration.ofSeconds(DEFAULT_WAIT_DURATION_FOR_ELEMENTS), Duration.ofMillis(200)
        ).ignoring(StaleElementReferenceException.class).until( pageIsReady() );
    }

    public Boolean waitForVisibilityOfElement(WebElementFacade element) {
        WebElement result = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_WAIT_DURATION_FOR_ELEMENTS)).until(
                ExpectedConditions.visibilityOf(element)
        );
        return result.isDisplayed();
    }

    public Boolean waitForVisibilityOfElements(List<WebElementFacade> elements) {
        elements.forEach(element -> waitForVisibilityOfElement(element));
        return elements.stream().allMatch(element -> element.isDisplayed());
    }

    public Boolean waitForSomeTime( long millis ){
        try{
            Thread.sleep(millis);
        }catch(InterruptedException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void openPage(Class<? extends PageObject> klass){
        Task.where("{0} open page", Open.browserOn().the(klass));
    }

    public Boolean evaluateJavascript(String jsCode, String expectedResult){
        return jsExecutor.executeScript(jsCode).toString().equalsIgnoreCase(expectedResult);
    }

    private Function<WebDriver, Boolean> pageIsReady(){
        return new Function<WebDriver, Boolean>(){
            @Override
            public Boolean apply(WebDriver driver) {
                return evaluateJavascript("return document.readyState", "complete");
            }

            @NotNull @Override
            public Function andThen(@NotNull Function after) {
                return Function.super.andThen(after);
            }

            @NotNull @Override
            public Function compose(@NotNull Function before) {
                return Function.super.compose(before);
            }
        };
    }

}
