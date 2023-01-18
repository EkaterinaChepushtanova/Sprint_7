import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {
    public Response create(Courier courier) {
        return given().log().all()
                .spec(getSpec())
                .body(courier)
                .when()
                .post("api/v1/courier");
    }

    public Response login(CourierCredentials credentials) {
        return given().log().all()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post("api/v1/courier/login");
    }

    public Response delete(int id) {
        String json = String.format("{\"id\": \"%d\"}", id);
        return given().log().all()
                .spec(getSpec())
                .body(json)
                .when()
                .delete("api/v1/courier/" + id);
    }
}
