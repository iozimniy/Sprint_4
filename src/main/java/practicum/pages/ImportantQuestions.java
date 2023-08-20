package practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static practicum.ConfigTest.BASE_URL;
import org.openqa.selenium.JavascriptExecutor;

public class ImportantQuestions {

    private WebDriver driver;

    //заголовок раздела
    private static By sectionHeader = By.xpath(".//div[starts-with(@class, 'Home_FourPart__')]");

    //массив воспросов
    private static String[] expectedAnswerText = new String[] {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    private static String[] expectedQuestionText = new String[] {
            "Сколько это стоит? И как оплатить?",
            "Хочу сразу несколько самокатов! Так можно?",
            "Как рассчитывается время аренды?",
            "Можно ли заказать самокат прямо на сегодня?",
            "Можно ли продлить заказ или вернуть самокат раньше?",
            "Вы привозите зарядку вместе с самокатом?",
            "Можно ли отменить заказ?",
            "Я жизу за МКАДом, привезёте?"
    };

    // список кнопок вопросов
    private static By questionButtons = By.xpath(".//div[contains(@id, 'accordion__heading-')]");

    //список панелей с ответами
    private static By[] answerPanels = new By[] {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };
//    не придумала, как приладить этот лист к явному ожиданию waitPanel (там требуется локатор), потому оставила костылину
//    private static By answerPanels = By.xpath(".//div[contains(@id, 'accordion__panel-')]");

    private static By answersTexts = By.xpath(".//div[contains(@id, 'accordion__panel-')]/p");

    public ImportantQuestions(WebDriver driver) {
        this.driver = driver;
    }

    //открываем главную страницу
    public void open() {
        driver.get(BASE_URL);
    }

    //получаем элемент списка текстов ожидаемых вопросов
    public String getExpectedQuestionText(int number) {
        return expectedQuestionText[number-1];
    }

    //получаем элемент списка текстов ожидаемых ответов
    public String getExpectedAnswerText(int number) {
        return expectedAnswerText[number-1];
    }

    //кликнуть по кнопке вопроса
    public void clickQuestion(int number) {
        driver.findElements(questionButtons).get(number-1).isEnabled();
        driver.findElements(questionButtons).get(number-1).click();
    }

    //ждём загрузки страницы
    public void waitMainPage() {
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(sectionHeader));
    }

    //прокручиваем до вопросов о важном
    public void scrollToQuestions() {
        WebElement element = driver.findElement(sectionHeader);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", element);
    }

    //ждём появления панели с ответом
    public void waitPanel(int number) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(answerPanels[number-1]));
    }

    //получаем текст вопроса
    public String getQuestionText(int number) {
        return driver.findElements(questionButtons).get(number-1).getText();
    }

    //получаем текст ответа
    public String getAnswerText(int number) {
        return driver.findElements(answersTexts).get(number-1).getText();
    }
}
