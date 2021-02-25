package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private static Faker faker = new Faker(new Locale("eng"));



        /*public static RegistrationInfo generateActiveUser() {
            Faker faker = new Faker(new Locale("eng"));
            return new RegistrationInfo(faker.name().firstName(), faker.internet().password(), "active");
        }

        public static RegistrationInfo generateBlockedUser() {
            Faker faker = new Faker(new Locale("eng"));
            return new RegistrationInfo(faker.name().firstName(), faker.internet().password(), "blocked");
        }*/


        public static class SendOnServer {
            private static RequestSpecification requestSpec = new RequestSpecBuilder()
                    .setBaseUri("http://localhost")
                    .setPort(9999)
                    .setAccept(ContentType.JSON)
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();

            public static void makeRequest(RegistrationInfo registrationInfo) {
                // сам запрос
                given() // "дано"
                        .spec(requestSpec) // указываем, какую спецификацию используем
                        .body(registrationInfo) // передаём в теле объект, который будет преобразован в JSON
                        .when() // "когда"
                        .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                        .then() // "тогда ожидаем"
                        .statusCode(200); // код 200 OK
            }


            public static RegistrationInfo generateUser(String status) {
                val user = new RegistrationInfo(faker.name().firstName(), faker.internet().password(), status);
                makeRequest(user);
                return user;
            }

        }
    }
}

