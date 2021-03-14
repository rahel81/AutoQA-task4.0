package ru.netology.delivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTestNew {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    int day = 7;

    String getData(int day) {
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }

    int selectYear(int day) {
        return LocalDate.now().plusDays(day).getYear() - LocalDate.now().plusDays(7).getYear();
    }

    int selectMonth(int day) {
        return LocalDate.now().plusDays(day).getMonthValue() - LocalDate.now().plusDays(7).getMonthValue();
    }

    @Test
    void shouldSendValidForm() {
        $("[placeholder=Город]").setValue("Ве");
        $$(".popup__content .menu-item__control").findBy(text("Великий Новгород")).click();
        $(".icon-button__text>.icon_name_calendar").click();
        if (selectYear(day) > 0) {
            for (int i = 0; i < selectYear(day); i++) {
                $(".calendar__arrow_direction_right calendar__arrow_double[data-step='12']").click();
            }
            if (selectMonth(day) > 0) {
                for (int i = 0; i < selectMonth(day); i++) {
                    $(".calendar__arrow_direction_right[data-step='1']").click();
                }
            } else {
                for (int i = 0; i > selectMonth(day); i--) {
                    $(".calendar__arrow_direction_left calendar__arrow_disabled[data-step='-1']").click();
                }
            }
        } else {
            for (int i = 0; i < selectMonth(day); i++) {
                $(".calendar__arrow_direction_right[data-step='1']").click();
            }
        }
        $$(".calendar__day").findBy(text(LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("d")))).click();
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(text("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000)
                .shouldHave(text("Успешно! Встреча успешно забронирована на " + getData(7)));
    }
}


