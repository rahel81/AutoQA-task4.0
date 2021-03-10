package ru.netology.delivery;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    LocalDate today = LocalDate.now();
    LocalDate newDay = today.plusDays(3);
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSendValidValue() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[placeholder]").setValue("newDay");
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $(".notification__content").waitUntil(visible, 15000)
                .$(withText("Встреча успешно забронирована на" + newDay));
    }

    @Test
    void shouldSendInvalidCity() {
        $("[data-test-id='city'] input").setValue("Луга");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[placeholder]").setValue("newDay");
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").waitUntil(visible, 15000)
                .$(withText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldSendInvalidName() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[placeholder]").setValue("newDay");
        $("[data-test-id=name] input").setValue("Elena");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").waitUntil(visible, 15000)
                .$(withText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSendInvalidPhone() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[placeholder]").setValue("newDay");
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+7910040805");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").waitUntil(visible, 15000)
                .$(withText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSendInvalidCheckbox() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[placeholder]").setValue("newDay");
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=agreement].input_invalid").waitUntil(visible, 15000)
                .$(withText("Я соглашаюсь с условиями обработки и использования моих персональных данных " +
                        "и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldSendEmptyFieldCity() {
        $("[data-test-id='city'] input").setValue("");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[placeholder]").setValue("newDay");
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").waitUntil(visible, 15000)
                .$(withText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendEmptyFieldName() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[placeholder]").setValue("newDay");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").waitUntil(visible, 15000)
                .$(withText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendEmptyFieldPhone() {
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[placeholder]").setValue("newDay");
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").waitUntil(visible, 15000)
                .$(withText("Поле обязательно для заполнения"));
    }
}
