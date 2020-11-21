package conductorApiTest;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class conductorApiTest {

    @Test
    public void createUser() {
        given()
             .contentType("application/json")
             .body("{\n" +
                     "    \"name\": \"morpheus\",\n" +
                     "    \"job\": \"leader\"\n" +
                     "}")
            .when()
            .post("https://reqres.in/api/users")
            .then()
            .log().all()
            .body("name", containsString("morpheus"))
            .statusCode(201);

    }

    @Test
    public void createUserJobIsObrigatory() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    public void searchUser() {
        given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void searchUserList() {
        given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void searchUserNotFound() {
        given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    public void updateUser() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"name\": \"paul rock\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .body("name", containsString("paul rock"))
                .statusCode(200);
    }

    @Test
    public void deleteUser() {
        given()
                .contentType("application/json")
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void updateUser2() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"name\": \"paul rock\",\n" +
                        "    \"job\": \"software qa\"\n" +
                        "}")
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .body("name", containsString("paul rock"))
                .statusCode(200);
    }

    @Test
    public void productList() {
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