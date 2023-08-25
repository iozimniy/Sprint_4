package practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.pages.CustomerData;
import practicum.pages.Header;
import practicum.pages.HowItWorks;
import practicum.pages.RentalData;

@RunWith(Parameterized.class)
public class OrderTests {

    @Rule
    public DriverRule rule = new DriverRule();

    private final String name;
    private final String surname;
    private final String address;
    private final String Station;
    private final String phone;
    private final String deliveryData;
    private final int rentPeriod;
    private final int checkboxColour;
    private final String comment;

    public OrderTests(String name, String surname, String address, String idStation, String phone, String deliveryData, int rentPeriod, int checkboxColour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.Station = idStation;
        this.phone = phone;
        this.deliveryData = deliveryData;
        this.rentPeriod = rentPeriod;
        this.checkboxColour = checkboxColour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrdersData() {
        return new Object[][]{
                {"Джейн", "Локсли", "Катманду", "Чеховская", "+79996663311", "28.08.2023", 3, 0, "Стучите громче!"},
                {"Джон", "Доу", "Тимбукту", "Рижская", "89997775522", "27.08.2023", 2, 1, "Позвоните за час"}
        };
    }

    //Проверяем, что оба входа на страницу оформления заказа рабочие
    @Test
    public void EnterToMakeOrderFromHeader() {
        Header header = new Header(rule.getDriver());

        header.open();
        CustomerData customerData = header.clickOnOrderButtonHeader();
        customerData.waitCustomerData();
    }

    @Test
    public void EnterToMakeOrderFromHowItWorks() {
        HowItWorks howItWorks = new HowItWorks(rule.getDriver());

        howItWorks.openAndScrollToOrderButtonHowItWorks();
        CustomerData customerData = howItWorks.clickOnOrderButtonHowItWorks();
        customerData.waitCustomerData();
    }

    @Test
    public void makeOrder() {
        CustomerData data = new CustomerData(rule.getDriver());

        data.open();
        data.fillInData(name, surname, address, Station, phone);
        RentalData rentalData = data.clickOnNextButton();
        rentalData.fillInRentData(deliveryData, rentPeriod, checkboxColour, comment);
        rentalData.clickFinishButton();
        rentalData.waitOrderNumderMessage();
    }
}
