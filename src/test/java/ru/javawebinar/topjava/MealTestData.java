package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    /*
     ('2015-6-1 14:0:0', 'Админ ланч', 510, 100001),
        ('2015-6-1 21:0:0', 'Админ ланч', 1500, 100001),
        ('2015-6-1 07:0:0', 'USER завтра', 510, 100000),
        ('2015-6-1 13:0:0', 'USER обед', 1500, 100000),
        ('2015-6-1 21:0:0', 'USER ужин', 1500, 100000),
        ('2015-6-2 7:0:0', 'USER завтра', 510, 100000),
        ('2015-6-2 13:0:0', 'USER обед', 1500, 100000),
        ('2015-6-2 21:0:0', 'USER ужин', 800, 100000);
     */

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;

    public static final Meal meal_admin100003 = new Meal(100003, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal meal_admin100004 = new Meal(100004, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ланч", 1500);
    public static final Meal meal_user100005 = new Meal(100005, LocalDateTime.of(2015, Month.JUNE, 1, 7, 0), "USER завтра", 510);
    public static final Meal meal_user100006 = new Meal(100006, LocalDateTime.of(2015, Month.JUNE, 1, 13, 0), "USER обед", 1500);
    public static final Meal meal_user100007 = new Meal(100007, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "USER удин", 1500);
    public static final Meal meal_user100008 = new Meal(100008, LocalDateTime.of(2015, Month.JUNE, 2, 7, 0), "USER завтра", 510);
    public static final Meal meal_user100009 = new Meal(100009, LocalDateTime.of(2015, Month.JUNE, 2, 13, 0), "USER обед", 1500);
    public static final Meal meal_user100010 = new Meal(100010, LocalDateTime.of(2015, Month.JUNE, 2, 21, 0), "USER ужин", 800);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2015, Month.JUNE, 1, 7, 0), "Admin завтрак", 700);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal_user100010);
        updated.setCalories(3000);
        updated.setDateTime(LocalDateTime.of(2000, Month.JUNE, 11, 17, 0));
        updated.setDescription("Five o'clock");
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields().isEqualTo(expected);
    }

}
