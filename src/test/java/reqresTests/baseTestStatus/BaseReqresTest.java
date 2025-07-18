package reqresTests.baseTestStatus;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import reqresTests.models.requests.AuthDto;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static reqresTests.TestData.createAuthDtoWithEmailAndPassword;

public class BaseReqresTest {

    private static final String AUTH_PATH = "/user-service/auth/login";

    protected static String token;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        RestAssured.baseURI = System.getProperty("baseUrl");
        RestAssured.basePath = System.getProperty("basePath");


    }

    @BeforeEach
    public void setUp() throws Exception {
        token = getRefreshToken();
    }

    private static String getRefreshToken() {

        AuthDto authDto =
                createAuthDtoWithEmailAndPassword(
                        "ivan@example.com",
                        "$2a$10$xJwL5v5zP5p5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5"
                );

        return given()
                .body(authDto)
                .contentType(JSON)
                .when().
                log().uri()
                .post(AUTH_PATH)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .asString();
    }
}
