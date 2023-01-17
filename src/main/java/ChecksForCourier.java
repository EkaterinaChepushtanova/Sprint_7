import io.restassured.response.Response;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class ChecksForCourier {
    public void createdSuccessfully(Response response) {
        response.then().log().all()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(SC_CREATED);
    }

    public void creatingTwiceFailed(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(SC_CONFLICT);
    }

    public void creatingWithoutPasswordFailed(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    public void loggedInSuccessfully(Response response) {
        response.then().log().all()
                .assertThat().body("id", notNullValue())
                .and()
                .statusCode(SC_OK);
    }

    public void loggedInWithoutPassword(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    public void loggedInWithWrongLogin(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(SC_NOT_FOUND);
    }
}