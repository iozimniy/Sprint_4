package practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static practicum.ConfigTest.BASE_URL;
import static practicum.ConfigTest.BASE_YANDEX;

public class Header {

    //кнопка для создания заказа
    private static final By orderButtonHeader = By.xpath(".//div[starts-with(@class, 'Header_Nav__')]/button[starts-with(@class, 'Button_Button__')]");
    //логотип самоката
    private static final By scooterLogo = By.xpath(".//img[@alt = 'Scooter']");
    //логотип яндекса
    private static final By yandexLogo = By.xpath(".//img[@alt = 'Yandex']");
    private final WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    //кликнуть по кнопке для оформления заказа
    public CustomerData clickOnOrderButtonHeader() {
        driver.findElement(orderButtonHeader).isEnabled();
        driver.findElement(orderButtonHeader).click();
        return new CustomerData(driver);
    }

    //откроем главную
    public void open() {
        driver.get(BASE_URL);
    }

    //кликнем на лого самоката
    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    //кликнем на лого Яндекса
    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    //подождём открытия страницы самоката
    public void waitMainPage() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlToBe(BASE_URL));
        } catch (TimeoutException e) {
            throw new AssertionError("Нет перехода на главную страницу Самоката");
        }
    }

    //подождём главной страницы Яндекса
    public void waitMainYandexPage() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlToBe(BASE_YANDEX));
        } catch (TimeoutException e) {
            throw new AssertionError("Нет перехода на главную страницу Яндекса");
        }
    }
}
