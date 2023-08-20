package practicum;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.time.Duration;

public class DriverRule extends ExternalResource {
    WebDriver driver;

    @Override
    protected void before() throws Throwable {
        if ("firefox".equals(System.getProperty("browser")))
            setUpFirefox();
        else
            setUpChrome();
    }

    private void setUpChrome() {

        System.setProperty("webriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/WebDriver/bin/chromedriver"))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary("E:/Prog/chrome for testing/chrome-win64/chrome.exe");

        driver = new ChromeDriver(service, options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void setUpFirefox() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        var service = new GeckoDriverService.Builder()
                .usingDriverExecutable(new File("C:/WebDriver/bin/geckodriver"))
                .build();

        var options = new FirefoxOptions()
                .setBinary("C:/Users/Китрисс/AppData/Local/Mozilla Firefox/firefox.exe");

        driver = new FirefoxDriver(service, options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void after() {
        driver.quit();
    }

}
