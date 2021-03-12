package ru.netology.delivery;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSendValidValue() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(CardDeliveryData.getData(3));
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000)
                .shouldHave(exactText("Успешно! Встреча успешно забронирована на " + CardDeliveryData.getData(3)));
    }

    @Test
    void shouldSendInvalidCity() {
        $("[data-test-id='city'] input").setValue("Луга");
        SelenideElement data = $("[data-test-id=date]");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(CardDeliveryData.getData(3));
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldSendInvalidName() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(CardDeliveryData.getData(3));
        $("[data-test-id=name] input").setValue("Elena");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSendInvalidPhone() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(CardDeliveryData.getData(3));
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+7910040805");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSendInvalidCheckbox() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(CardDeliveryData.getData(3));
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=agreement] .checkbox__text")
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных "));
    }

    @Test
    void shouldSendEmptyFieldCity() {
        $("[data-test-id='city'] input").setValue("");
        SelenideElement data = $("[data-test-id=date]");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(CardDeliveryData.getData(3));
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendEmptyFieldName() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(CardDeliveryData.getData(3));
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendEmptyFieldPhone() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(CardDeliveryData.getData(3));
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
