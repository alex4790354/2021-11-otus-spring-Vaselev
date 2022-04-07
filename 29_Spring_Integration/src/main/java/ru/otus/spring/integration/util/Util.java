package ru.otus.spring.integration.util;

import com.github.javafaker.Faker;
import java.util.function.Consumer;


public class Util {

    private static final Faker FAKER = Faker.instance();

    public static Faker faker(){
        return FAKER;
    }

}
