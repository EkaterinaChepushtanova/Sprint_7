import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class ListOfOrdersTest {

    private OrderClient client = new OrderClient();
    private OrderChecks checks = new OrderChecks();

    @Test
    @DisplayName("Check that the list of orders is displayed")
    public void listOfOrdersDisplayedTest() {
        Order order = new Order();
        Response response = client.getOrder(order);
        checks.orderList(response);
    }
}
