package practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static practicum.ConfigTest.BASE_URL;

public class HowItWorks {

    //кнопка для оформления заказа
    private static final By orderButtonHowItWorks = By.xpath(".//div[starts-with(@class, 'Home_FinishButton__')]/button[starts-with(@class, 'Button_Button__')]");
    private final WebDriver driver;

    public HowItWorks(WebDriver driver) {
        this.driver = driver;
    }

    //открыть страницу и промотать до кнопки
    public void openAndScrollToOrderButtonHowItWorks() {
        driver.get(BASE_URL);
        WebElement element = driver.findElement(orderButtonHowItWorks);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
    }

    //кликнуть по кнопке для заказа
    public CustomerData clickOnOrderButtonHowItWorks() {
        driver.findElement(orderButtonHowItWorks).isEnabled();
        driver.findElement(orderButtonHowItWorks).click();
        return new CustomerData(driver);
    }
}
