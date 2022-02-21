package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.MealTestData.getNew;
import static ru.javawebinar.topjava.MealTestData.getUpdated;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(adminMeal03.getId(), ADMIN_ID);
        assertMatch(meal, adminMeal03);
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(adminMeal03.getId(), USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(userMeal05.getId(), ADMIN_ID));
    }


    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(adminMeal03.getId(), USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(NOT_EXISTING_MEAL_ID, ADMIN_ID));
        assertThrows(NotFoundException.class, () -> service.get(NOT_EXISTING_MEAL_ID, NOT_FOUND));
    }

    @Test
    public void delete() {
        service.delete(adminMeal03.getId(), ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.get(adminMeal03.getId(), ADMIN_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(adminMeal03.getId(), USER_ID));
        assertThrows(NotFoundException.class, () -> service.delete(userMeal05.getId(), ADMIN_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(adminMeal03.getId(), NOT_FOUND));
        assertThrows(NotFoundException.class, () -> service.delete(NOT_EXISTING_MEAL_ID, ADMIN_ID));
        assertThrows(NotFoundException.class, () -> service.delete(NOT_EXISTING_MEAL_ID, NOT_FOUND));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> all = service.getBetweenInclusive(LocalDate.of(2015, Month.JUNE, 2),
                LocalDate.of(2015, Month.JUNE, 2), USER_ID);
        assertMatch(all, userMeal10, userMeal09, userMeal08);
    }

    @Test
    public void getBetweenInclusiveBordersNull() {
        List<Meal> all = service.getBetweenInclusive(null, null, ADMIN_ID);
        assertMatch(all, adminMeal04, adminMeal03);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(ADMIN_ID);
        assertMatch(all, adminMeal04, adminMeal03);
    }

    @Test
    public void duplicateMealCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, userMeal10.getDateTime(), "USER second Ужин", 1800), USER_ID));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(100010, USER_ID), getUpdated());
    }

    @Test
    public void updateNotOwn() {
        Meal updated = getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(), ADMIN_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, ADMIN_ID), newMeal);
    }
}