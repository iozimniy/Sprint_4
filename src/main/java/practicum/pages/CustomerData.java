package practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static practicum.ConfigTest.ORDER_URL;

public class CustomerData {

    WebDriver driver;

    //Заголовок
    private static By orderHeader = By.xpath(".//div[starts-with(@class, 'Order_Header')]");
    //поле Имя
    private static By nameField = By.xpath(".//input[@placeholder = '* Имя']");
    //поле Фамилия
    private static By surnameField = By.xpath(".//input[@placeholder = '* Фамилия']");
    //поле Адресс
    private static By addressField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //поле Станция
    private static By stationField = By.xpath(".//input[@placeholder = '* Станция метро']");
    //поле Телефон
    private static By phoneField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //кнопка для перехода на следующий экран
    private static By nextButtom = By.xpath(".//div[starts-with(@class, 'Order_NextButton')]/button[starts-with(@class, 'Button_Button')]");

    public CustomerData(WebDriver driver) {
        this.driver = driver;
    }

    //открыть страницу
    public void open() {
        driver.get(ORDER_URL);
    }

    //заполним поле Имя
    public void fillInName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //заполним поле Фамилия
    public void fillInSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    //заполним поле Адрес
    public void fillInAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //выберем станцию
    public void choiceStation(String station) {
        driver.findElement(stationField).sendKeys(station);
        driver.findElement(By.xpath(".//button[starts-with(@class, 'Order_SelectOption')]/div[contains(text(), station)]")).click();
    }

    //заполним поле Телефон
    public void fillInPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    //заполним все поля сразу
    public void fillInData(String name, String surname, String address, String idStation, String phone) {
        fillInName(name);
        fillInSurname(surname);
        fillInAddress(address);
        choiceStation(idStation);
        fillInPhone(phone);
    }

    //кликнем по кнопке для перехода
    public RentalData clickOnNextButton() {
        driver.findElement(nextButtom).click();
        return new RentalData(driver);
    }

    //ждём загрузки страницы
    public void waitCustomerData() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
    }
}
