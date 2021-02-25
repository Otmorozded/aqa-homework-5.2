package ru.netology;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.Registration.SendOnServer.generateUser;


class AuthTest {
    @BeforeEach
    public void setUpAll() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldPassWithActiveUser() {
        val user = generateUser("Active");



        $("[name=login]").setValue(user.getLogin());
        $("[name=password]").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible);
    }








    /*@Test
    void shouldPassWithActiveUser() {
        RegistrationInfo dataOrderCard= DataGenerator.Registration.generateActiveUser();
        DataGenerator.SendOnServer.setUpAll(dataOrderCard);
        $("[name=login]").setValue(dataOrderCard.getLogin());
        $("[name=password]").setValue(dataOrderCard.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible);
    }*/

    /*@Test
    void shouldPassWithBlockedUser() {
        RegistrationInfo dataOrderCard= DataGenerator.Registration.generateBlockedUser();
        DataGenerator.SendOnServer.setUpAll(dataOrderCard);
        $("[name=login]").setValue(dataOrderCard.getLogin());
        $("[name=password]").setValue(dataOrderCard.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Пользователь заблокирован")).shouldBe(Condition.visible);
    }

    @Test
    void shouldFailWithInvalidLogin() {
        RegistrationInfo dataOrderCard= DataGenerator.Registration.generateActiveUser();
        DataGenerator.SendOnServer.setUpAll(dataOrderCard);

        $("[name=login]").setValue("12345");
        $("[name=password]").setValue(dataOrderCard.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин")).shouldBe(Condition.visible);
    }

    @Test
    void shouldFailWithInvalidPassword() {
        RegistrationInfo dataOrderCard= DataGenerator.Registration.generateActiveUser();
        DataGenerator.SendOnServer.setUpAll(dataOrderCard);
        $("[name=login]").setValue(dataOrderCard.getLogin());
        $("[name=password]").setValue("qwerty");
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }*/


}
