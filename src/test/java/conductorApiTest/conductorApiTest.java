package conductorApiTest;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class conductorApiTest {

    @Test
    public void apiTest() {
        Response response =
        given()
        .when()
            .get("https://reqres.in/api/unknown");
        response.then()
            .statusCode(200)
            .log().all();

        String lista = response.jsonPath().getString("data");
        System.out.println("Lista de produtos: " + lista);
    }
}