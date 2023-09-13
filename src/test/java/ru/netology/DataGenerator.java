package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    static Faker faker = new Faker(new Locale("ru"));

    public String generateDate(long days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String generateCity() {
        String city = faker.address().city();
        return city;
    }

    public static String generateName() {
        String name = faker.name().firstName() + " " + faker.name().lastName();
        return name;
    }

    public static String generatePhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }
}