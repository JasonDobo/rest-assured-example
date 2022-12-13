import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;

public class TestCases {

    @Test
    public void firstTestCase() {
        String url = "https://reqres.in/api/users?page=2";
        given()
                .when()
                .get(url)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bitCoinPrice() {
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("json/currentprice.json").getFile());

        given()
                .when()
                        .get(url)
                .then()
                        .assertThat()
                        .statusCode(200)
                        .body(JsonSchemaValidator.matchesJsonSchema(file));
    }
}
