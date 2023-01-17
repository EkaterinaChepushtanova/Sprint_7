import io.restassured.response.Response;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class OrderChecks {

    public void orderCreated(Response response) {
        response.then().log().all()
                .extract().response();
        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(SC_CREATED);
    }

    public void orderList(Response response) {
        response.then().log().all()
                .assertThat().body("orders", notNullValue())
                .and()
                .statusCode(SC_OK);
    }
}
