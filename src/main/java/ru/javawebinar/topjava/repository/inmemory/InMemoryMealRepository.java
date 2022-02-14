package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    {
        repository.put(1, new ConcurrentHashMap<>());
        for (Meal meal : MealsUtil.meals)
            this.save(1, meal);
    }

    @Override
    public Meal save(int authUserId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(authUserId).put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.get(authUserId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int authUserId, int id) {
        return repository.get(authUserId).remove(id) != null;
    }

    @Override
    public Meal get(int authUSerId, int id) {
        return repository.get(authUSerId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int authUserId) {
        return repository.get(authUserId).values().stream().sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getFiltered(int autheUserId, LocalDate startDate, LocalDate endDate) {
        Predicate<Meal> startDatePredicat = meal -> startDate == null || meal.getDate().compareTo(startDate) >= 0;
        Predicate<Meal> endDatePredicate = meal -> endDate == null || meal.getDate().compareTo(endDate) <= 0;
        return getAll(autheUserId).stream().filter(startDatePredicat.and(endDatePredicate)).collect(Collectors.toList());
    }
}

