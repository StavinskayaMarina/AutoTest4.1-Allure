package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DataGeneratorTest {

    DataGenerator plan = new DataGenerator();

    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void createUrl() {
        open("http://localhost:9999/");
    }

    @Test
    void successfulFormSend() {
        $("[data-test-id='city'] input").setValue(plan.generateCity());
        $(".menu").click();
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(plan.generateDate(5));
        $("[data-test-id='name'] input").setValue(plan.generateName());
        $("[data-test-id='phone'] input").setValue(plan.generatePhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + plan.generateDate(5)));
        $(".icon-button.notification__closer").click();
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(plan.generateDate(7));
        $(".button").click();
        $("[data-test-id='replan-notification'] .notification__title").shouldBe(visible).shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] .button").click();
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + plan.generateDate(7)));
    }
}