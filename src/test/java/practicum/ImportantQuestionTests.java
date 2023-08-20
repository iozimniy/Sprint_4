package practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.pages.ImportantQuestions;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ImportantQuestionTests {

    @Rule
    public DriverRule rule = new DriverRule();

    private int questionNumber;

    public ImportantQuestionTests(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Parameterized.Parameters
    public static Object[] getFAQ() {
        return new Object[] {
                1,
                3,
                5
        };
    }

    @Test
    //Проверяем, что вопросы совпадают с ожидаемыми
    public void testFAQQuestion() {
        ImportantQuestions ask = new ImportantQuestions(rule.getDriver());

        ask.open();
        ask.waitMainPage();
        ask.scrollToQuestions();
        assertEquals("Ошибка в вопросе на главной странице!",ask.getExpectedQuestionText(questionNumber),
                ask.getQuestionText(questionNumber));

    }

    @Test
    //проверяем, что ответы совпадают с ожидаемыми
    public void testFAQAnswers() {
        ImportantQuestions faq = new ImportantQuestions(rule.getDriver());

        faq.open();
        faq.waitMainPage();
        faq.scrollToQuestions();
        faq.clickQuestion(questionNumber);
        faq.waitPanel(questionNumber);
        assertEquals("Ошибка в ответе на главной странице!",faq.getExpectedAnswerText(questionNumber),
                faq.getAnswerText(questionNumber));
    }
}
