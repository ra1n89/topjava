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
    }

    @Override
    public void updateMeal(int id, LocalDateTime localDateTime, String description, int calories) {
        meals.set(meals.indexOf(findById(id)), new Meal(id, localDateTime, description,  calories));
    }

    @Override
    public void deleteMeal(int id) {

        meals.remove(findById(id));
    }

    public Meal findById(int id){
        for (Meal meal: meals){
            if(meal.getId()==id){
                return meal;
            }
        }
        return null;
    }
}
