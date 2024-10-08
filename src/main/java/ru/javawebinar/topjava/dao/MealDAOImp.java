package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MealDAOImp implements MealDAO {

    public static List<Meal> meals = new ArrayList<>();
    public static int id;
    @Override
    public void addMeal(LocalDateTime localDateTime, String description, int calories) {
        id = id + 1;
        meals.add(new Meal(id, localDateTime, description,  calories  ));
        System.out.println(meals);
    }

    @Override
    public void updateMeal() {

    }

    @Override
    public void deleteMeal() {

    }
}
