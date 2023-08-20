package practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentalData {

    WebDriver driver;

    //поле Календарь
    private static By calendarField = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    //поле Срок аренды
    private static By periodField = By.className("Dropdown-arrow");
    // чекбокс
    private static By checkboxColourButtons = By.xpath(".//input[@type = 'checkbox']");
    //поле для комментарией
    private static By commentField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    //кнопка для оформления заказа
    private static By finishButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text() = 'Заказать']");

    //всплывающее окно с номером заказа
    private static By orderNumdermMessage = By.xpath(".//div[contains(@class, 'Order_NextButton')]/button[text() = 'Посмотреть статус']");

    public RentalData(WebDriver driver) {
        this.driver = driver;
    }

    //заполним поле даты
    public void chooseDeliveryDay(String deliveryData) {
        driver.findElement(calendarField).sendKeys(deliveryData);
    }

    //заполним срок аренды
    public void fillInPeriod(int days) {
        driver.findElement(periodField).isEnabled();
        driver.findElement(periodField).click();
        driver.findElements(By.className("Dropdown-option")).get(days).click();
    }

    //выбираем цвет в чекбоксе
    public void chooseColour(int colour) {
        driver.findElements(checkboxColourButtons).get(colour).click();
    }
    //указываем комментарий для курьера
    public void leaveComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    //кликнуть на кнопку для оформления заказа
    public void clickFinishButton() {
        driver.findElement(finishButton).click();
    }

    //заполнить всее поля
    public void fillInRentData(String deliveryData, int days, int colour, String comment) {
        chooseDeliveryDay(deliveryData);
        fillInPeriod(days);
        chooseColour(colour);
        leaveComment(comment);
    }

    //дождаться всплывающего окна с номером заказа
    public void waitOrderNumdermMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderNumdermMessage));
    }
}
