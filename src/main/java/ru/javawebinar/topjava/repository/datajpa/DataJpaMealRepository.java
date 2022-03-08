package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository, CrudUserRepository crudUserRepository) {
        this.crudRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        var user = crudUserRepository.getById(userId);
        meal.setUser(user);
        if (meal.isNew()) {
            return crudRepository.save(meal);
        } else if (get(meal.id(), userId) == null)
            return null;
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
         return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.getMealByIdAndUser(id, crudUserRepository.getById(userId));
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findByUserOrderByDateTimeDesc(crudUserRepository.getById(userId));
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudRepository.findAllByUserAndDateTimeGreaterThanEqualAndDateTimeLessThanOrderByDateTimeDesc(
                crudUserRepository.getById(userId),startDateTime,endDateTime);
    }
}
