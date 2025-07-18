package reqresTests;

import org.junit.jupiter.api.Test;
import reqresTests.baseTestStatus.BaseReqresTest;
import reqresTests.models.requests.AuthDto;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static reqresTests.TestData.createAuthDtoWithEmailAndPassword;
import static reqresTests.utils.RandomNumber.randomNumber;

public class LoginTests extends BaseReqresTest {

    private static final String AUTH_PATH = "/user-service/auth/login";
    private static final String ADD_EMAIL_PATH = "/user-service/user/new-email/{email}";
    private static final String TRANSFER_PATH = "/user-controller/transfer";
    private static final String USER_ID = "1";



    private static final String TRANSFER_DTO = """
            {
                "id": 2,
                "amount": 10
            }""";


    @Test
    void successfulLoginTest() {

        AuthDto authDto =
                createAuthDtoWithEmailAndPassword(
                        "ivan@example.com",
                        "$2a$10$xJwL5v5zP5p5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5"
                );

        String responseToken = given()
                .log().uri()
                .log().headers()
                .log().body()
                .body(authDto)
                .contentType(JSON)
                .when().
                log().uri()
                .post(AUTH_PATH)
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .asString();

        assertEquals(responseToken, token);
    }

    @Test
    void addEmailTest() {
        given()
                .header("Authorization", "Bearer " + token)
                .header("userId", USER_ID)
                .pathParam("email", "max_dr" + randomNumber() + "@mail.ru")
                .log().uri()
                .log().headers()
                .log().parameters()
                .log().body()
                .when().
                log().uri()
                .post(ADD_EMAIL_PATH)
                .then()
                .log().all()
                .statusCode(200)
                .contentType(TEXT);
    }

    @Test
    void transferTest() {
        given()
                .header("Authorization", "Bearer " + token)
                .header("userId", USER_ID)
                .body(TRANSFER_DTO)
                .contentType(JSON)
                .log().uri()
                .log().headers()
                .log().parameters()
                .log().body()
                .when().
                log().uri()
                .post(TRANSFER_PATH)
                .then()
                .log().all()
                .statusCode(200)
                .contentType(JSON);
    }
}
