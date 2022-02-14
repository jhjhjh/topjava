package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

// TODO add userId
public interface MealRepository {
    // null if updated meal does not belong to userId
    Meal save(int authUserId, Meal meal);

    // false if meal does not belong to userId
    boolean delete(int authUserId, int id);

    // null if meal does not belong to userId
    Meal get(int authUserId, int id);

    // ORDERED dateTime desc
    Collection<Meal> getAll(int authUserId);

    Collection<Meal> getFiltered(int autheUserId, LocalDate startDate, LocalDate endDate);
}
