package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;


public class MealTestData {

    public static final int NOT_EXISTING_MEAL_ID = 10;

    public static final Meal adminMeal03 = new Meal(START_SEQ + 3, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal adminMeal04 = new Meal(START_SEQ + 4, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ланч", 1500);
    public static final Meal userMeal05 = new Meal(START_SEQ + 5, LocalDateTime.of(2015, Month.JUNE, 1, 7, 0), "USER завтра", 510);
    public static final Meal userMeal06 = new Meal(START_SEQ + 6, LocalDateTime.of(2015, Month.JUNE, 1, 13, 0), "USER обед", 1500);
    public static final Meal userMeal07 = new Meal(START_SEQ + 7, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "USER удин", 1500);
    public static final Meal userMeal08 = new Meal(START_SEQ + 8, LocalDateTime.of(2015, Month.JUNE, 2, 7, 0), "USER завтра", 510);
    public static final Meal userMeal09 = new Meal(START_SEQ + 9, LocalDateTime.of(2015, Month.JUNE, 2, 13, 0), "USER обед", 1500);
    public static final Meal userMeal10 = new Meal(START_SEQ + 10, LocalDateTime.of(2015, Month.JUNE, 2, 21, 0), "USER ужин", 800);
    public static final Meal userMeal11 = new Meal(START_SEQ + 11, LocalDateTime.of(2022, Month.FEBRUARY, 20, 0, 0), "USER ужин", 800);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2015, Month.JUNE, 1, 7, 1), "Admin завтрак", 700);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal10);
        updated.setCalories(3000);
        updated.setDateTime(LocalDateTime.of(2000, Month.JUNE, 11, 17, 0));
        updated.setDescription("Five o'clock");
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

}
