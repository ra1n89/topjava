package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealDao implements MealDao {


    private List<Meal> meals =  new ArrayList<>();

    private AtomicInteger atomicInt = new AtomicInteger(0);



    public InMemoryMealDao() {
        add(new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal add(Meal meal) {

        meals.add(meal);
        return meal;
    }

    @Override
    public Meal update(Meal meal) {

        int index = meals.indexOf(findById(meal.getId()));
        meals.set(index, meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        meals.remove(findById(id));
    }

    public Meal findById(int id) {
        for (Meal meal : meals) {
            if (meal.getId() == id) {
                return meal;
            }
        }
        return null;
    }

    public List<Meal> getAll() {
        return new ArrayList<>(meals);
    }

    public int increaseId() {
        return atomicInt.incrementAndGet();
    }
}
