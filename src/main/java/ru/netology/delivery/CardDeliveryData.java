package ru.netology.delivery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CardDeliveryData {
    public static String getData(int day) {
        String inputData = LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return inputData;
    }
}
