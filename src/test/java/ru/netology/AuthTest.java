package ru.netology;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.Registration.*;
class AuthTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }
    @Test
    void shouldPassWithActiveUser() {
        val validUser = generateUser("active");
        $("[name=login]").setValue(validUser.getLogin());
        $("[name=password]").setValue(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(visible);
    }
    @Test
    void shouldPassWithBlockedUser() {
        val blockedUser = generateUser("blocked");
        $("[name=login]").setValue(blockedUser.getLogin());
        $("[name=password]").setValue(blockedUser.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Пользователь заблокирован")).shouldBe(visible);
    }
    @Test
    void shouldFailWithInvalidLogin() {
        val wrongLoginUser = generateWrongLoginUser("active");
        $("[name=login]").setValue(wrongLoginUser.getLogin());
        $("[name=password]").setValue(wrongLoginUser.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);
    }
    @Test
    void shouldFailWithInvalidPassword() {
        val wrongLoginUser = generateWrongPasswordUser("active");
        $("[name=login]").setValue(wrongLoginUser.getLogin());
        $("[name=password]").setValue(wrongLoginUser.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);
    }
}





