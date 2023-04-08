package starter.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import starter.page.bugaboo.BugabooCustomerContactForm;
import starter.page.bugaboo.BugabooHomePage;
import starter.page.yavlena.YavlenaHomePage;

public class NavigateTo {
    public static Performable theBugabooHomePage() {
        return Task.where("{0} opens the Bugaboo home page",
                Open.browserOn().the(BugabooHomePage.class));
    }

    public static Performable theBugabooCustomerContactForm() {
        return Task.where("{0} opens the Bugaboo Consumer Contact Form page",
                Open.browserOn().the(BugabooCustomerContactForm.class));
    }

    public static Performable theYavlenaBrokerPage(){
        return Task.where("{0} opens Yavlena Broker page",
                Open.browserOn().the(YavlenaHomePage.class));
    }
}
