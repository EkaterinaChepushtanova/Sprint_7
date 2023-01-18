import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

public class CourierCreateTest {
    private CourierClient client = new CourierClient();
    private ChecksForCourier checks = new ChecksForCourier();
    private CourierGenerator generator = new CourierGenerator();
    private int id;

    @Test
    @DisplayName("Check that courier can be created")
    public void courierCanBeCreatedTest() {
        Courier courier = generator.randomData();
        Response response = client.create(courier);
        checks.createdSuccessfully(response);
        Response loginResponse = client.login(CourierCredentials.from(courier));
        loginResponse.then().log().all();
        id = loginResponse.path("id");
    }

    @Test
    @DisplayName("Check that courier can't be created twice with the same data")
    public void courierCannotBeCreatedTwiceTest() {
        client.create(generator.getDefault());
        Response response = client.create(generator.getDefault());
        checks.creatingTwiceFailed(response);
    }

    @Test
    @DisplayName("Check that courier can't be created without all necessary data")
    public void courierCannotBeCreatedWithoutPasswordTest() {
        Response response = client.create(generator.notFullData());
        checks.creatingWithoutPasswordFailed(response);
    }

    @After
    public void cleanUp() {
        if (id > 0) {
            client.delete(id);
        }
    }
}