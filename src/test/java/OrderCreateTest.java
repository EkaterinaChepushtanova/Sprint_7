import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)

public class OrderCreateTest {
    private OrderClient client = new OrderClient();
    private OrderChecks checks = new OrderChecks();
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List color;

    public OrderCreateTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "firstname: {0}, lastName: {1}, address: {2}, metroStation: {3}, phone: {4}, rentTime: {5}, deliveryDate: {6}, comment: {7}, color: {8}")
    public static Object[][] getOrderInfo() {
        return new Object[][]{
                {"Екатерина", "Иванова", "Тольятти, Советская 80", "7", "89613458234", 2, "2023-10-31", "домофон не работает", Arrays.asList("GREY")},
                {"Владимир", "Кошелев", "Москва, Ленина 18", "3", "89277744532", 4, "2023-02-04", "доставить без опозданий", Arrays.asList("BLACK")},
                {"Марина", "Зеленова", "Сочи, ГЭС 43", "1", "89094582412", 3, "2023-01-02", "позвоните, как подъедете", Arrays.asList("GREY", "BLACK")},
                {"Василий", "Трямкин", "Волгоград, Мира 12", "4", "89375621192", 1, "2023-05-25", "5-й подъезд", Arrays.asList("")},
        };
    }

    @Test
    @DisplayName("Check that the order can be created with different colors")
    public void orderCreationTest() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = client.create(order);
        checks.orderCreated(response);
    }
}

