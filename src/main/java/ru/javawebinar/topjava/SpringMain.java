package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            mealRestController.getAll().stream().forEach(System.out::println);
            mealRestController.create(new Meal(LocalDateTime.of(2020, 01, 31, 15, 00), "Чипсы", 20));
            mealRestController.getAll().stream().forEach(System.out::println);
            mealRestController.delete(8);
            mealRestController.getAll().stream().forEach(System.out::println);
            //mealRestController.delete(8);
            //mealRestController.getAll().stream().forEach(System.out::println);
            mealRestController.getFiltered(null,null, null, null).stream().forEach(System.out::println);
            mealRestController.getFiltered(LocalDate.of(2020, 01, 31), LocalTime.of(12,00), LocalDate.of(2020, 01, 31), LocalTime.of(13,01)).stream().forEach(System.out::println);
            mealRestController.getFiltered(LocalDate.of(2020, 01, 31), null, LocalDate.of(2020, 01, 31), null).stream().forEach(System.out::println);
            //mealRestController.getAll().stream().forEach(System.out::println);
        }
    }
}
