package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }


    public Meal get(int authUserId, int id) {
        return checkNotFoundWithId(repository.get(authUserId, id), id);
    }

    public Collection<Meal> getAll(int authUserId) {
        return repository.getAll(authUserId);
    }

    public Collection<Meal> getFiltered(int autheUserId, LocalDate startDate, LocalDate endDate) {
        return repository.getFiltered(autheUserId, startDate, endDate);
    }

    public Meal create(int authUserId, Meal meal) {
        return repository.save(authUserId, meal);
    }

    public void delete(int authUserId, int id) {
        checkNotFoundWithId(repository.delete(authUserId, id), id);
    }

    public void update(int authUserId, Meal meal, int id) {
        checkNotFoundWithId(repository.save(authUserId, meal), meal.getId());
    }
}