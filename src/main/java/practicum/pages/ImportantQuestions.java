package practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static practicum.ConfigTest.BASE_URL;

public class ImportantQuestions {

    //заголовок раздела
    private static final By sectionHeader = By.xpath(".//div[starts-with(@class, 'Home_FourPart__')]");
    // список кнопок вопросов
    private static final By questionButtons = By.xpath(".//div[contains(@id, 'accordion__heading-')]");
    //список панелей с ответами
    //не придумала, как приладить этот лист к явному ожиданию waitPanel (там требуется локатор), потому оставила костылину
    //private static By answerPanels = By.xpath(".//div[contains(@id, 'accordion__panel-')]");
    private static final By[] answerPanels = new By[]{
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };
    private static final By answersTexts = By.xpath(".//div[contains(@id, 'accordion__panel-')]/p");
    private final WebDriver driver;

    public ImportantQuestions(WebDriver driver) {
        this.driver = driver;
    }

    //открываем главную страницу
    public void open() {
        driver.get(BASE_URL);
    }

    //кликнуть по кнопке вопроса
    public void clickQuestion(int number) {
        driver.findElements(questionButtons).get(number - 1).isEnabled();
        driver.findElements(questionButtons).get(number - 1).click();
    }

    //ждём загрузки страницы
    public void waitMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(sectionHeader));
    }

    //прокручиваем до вопросов о важном
    public void scrollToQuestions() {
        WebElement element = driver.findElement(sectionHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
    }

    //ждём появления панели с ответом
    public void waitPanel(int number) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.withMessage("Не появляется панель с ответом!");
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerPanels[number - 1]));
    }

    //получаем текст вопроса
    public String getQuestionText(int number) {
        return driver.findElements(questionButtons).get(number - 1).getText();
    }

    //получаем текст ответа
    public String getAnswerText(int number) {
        return driver.findElements(answersTexts).get(number - 1).getText();
    }
}
