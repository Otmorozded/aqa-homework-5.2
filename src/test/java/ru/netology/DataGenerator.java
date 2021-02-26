package ru.netology;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;
import java.util.Locale;
import static io.restassured.RestAssured.given;
import static ru.netology.DataGenerator.SendOnServer.makeRequest;
public class DataGenerator {
    private DataGenerator() {
    }
    public static class Registration {
        private static Faker faker = new Faker(new Locale("eng"));
        public static RegistrationInfo generateUser(String status) {
            val user = new RegistrationInfo(faker.name().firstName(), faker.internet().password(), status);
            makeRequest(user);
            return user;
        }
        public static RegistrationInfo generateWrongLoginUser(String status) {
            val password = faker.internet().password();
            makeRequest(new RegistrationInfo(faker.name().firstName(), password, status));
            return new RegistrationInfo(faker.name().firstName(), password, status);
        }
        public static RegistrationInfo generateWrongPasswordUser(String status) {
            val login = faker.name().firstName();
            makeRequest(new RegistrationInfo(login, faker.internet().password(), status));
            return new RegistrationInfo(login, faker.internet().password(), status);
        }
    }
    public static class SendOnServer {
        private static RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        public static void makeRequest(RegistrationInfo registrationInfo) {
            given()
                    .spec(requestSpec)
                    .body(registrationInfo)
                    .when()
                    .post("/api/system/users")
                    .then()
                    .statusCode(200);
        }
    }
}

