package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.UserServlet;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import static org.slf4j.LoggerFactory.getLogger;

public class InMemoryMealDao implements MealDao {

    private static final Logger log = getLogger(UserServlet.class);

    private List<Meal> mealsList =  new ArrayList<>();

    private AtomicInteger atomicInt = new AtomicInteger(1);

    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    public InMemoryMealDao() {
        add(new Meal(getId(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(getId(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(getId(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(getId(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(getId(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(getId(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(getId(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal add(Meal meal) {
        meals.put(atomicInt.get(), meal);
        log.info("AtomicInt = {}", atomicInt.get());
        atomicInt.incrementAndGet();
        return meal;
    }

    @Override
    public Meal update(Meal meal) {
        meals.replace(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    public Meal findById(int id) {
        return meals.get(id);
    }

    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    public int getId() {
        return atomicInt.get();
    }
}
