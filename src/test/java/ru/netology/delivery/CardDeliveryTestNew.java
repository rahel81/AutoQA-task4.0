package ru.netology.delivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTestNew {
    int today = LocalDate.now().getMonthValue();
    int newToday = LocalDate.now().plusWeeks(1).getMonthValue();
    String newDay = String.valueOf(LocalDate.now().plusWeeks(1).getDayOfMonth());
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSendValidForm() {
        $("[placeholder=Город]").setValue("Ве");
        $$(".popup__content .menu-item__control").findBy(text("Великий Новгород")).click();
        $(".input__icon").click();
        for (; (newToday - today) > 0; newToday++) {
            $(".calendar__title").$(".calendar__arrow calendar__arrow_direction_right").click();
        }
        $$("td.calendar__day").find(text(newDay)).click();
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $(".notification__content").waitUntil(visible, 15000)
                .$(withText("Встреча успешно забронирована на"));
    }
}


