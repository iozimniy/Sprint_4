package practicum;

import org.junit.Rule;
import org.junit.Test;
import practicum.pages.Header;

public class LogoTests {

    @Rule
    public DriverRule rule = new DriverRule();

    @Test
    public void testScooterLogo() {
        Header header = new Header(rule.getDriver());

        header.open();
        header.clickScooterLogo();
        header.waitMainPage();
    }

    @Test
    public void testYandexLogo() {
        Header header = new Header(rule.getDriver());

        header.open();
        header.clickYandexLogo();
        header.waitMainYandexPage();
    }


}
