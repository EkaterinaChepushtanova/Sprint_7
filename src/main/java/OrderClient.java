import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {

    public Response create(Order order) {
        return given().log().all()
                .spec(getSpec())
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    public Response getOrder(Order order) {
        return given().log().all()
                .spec(getSpec())
                .body(order)
                .when()
                .get("/api/v1/orders");
    }
}
