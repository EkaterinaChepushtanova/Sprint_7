import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

import org.junit.Test;


public class LoginCourierTest {

    private CourierClient client = new CourierClient();
    private ChecksForCourier checks = new ChecksForCourier();
    private CourierGenerator generator = new CourierGenerator();

    @Test
    @DisplayName("Check that the courier can log in to the system")
    public void loginCourierTest() {
        Courier courier = generator.loginData();
        Response response = client.login(CourierCredentials.from(courier));
        checks.loggedInSuccessfully(response);
    }

    @Test
    @DisplayName("Check that the courier can not log in to the system without necessary data")
    public void loginCourierWithoutPasswordTest() {
        Courier courier = generator.notFullData();
        Response response = client.login(CourierCredentials.from(courier));
        checks.loggedInWithoutPassword(response);
    }

    @Test
    @DisplayName("Check that the courier can not log in to the system with wrong data")
    public void loginCourierWithWrongLoginTest() {
        Courier courier = generator.randomData();
        Response response = client.login(CourierCredentials.from(courier));
        checks.loggedInWithWrongLogin(response);
    }
}