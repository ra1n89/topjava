package ru.javawebinar.topjava.dao;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDAO {

        void addMeal(LocalDateTime localDateTime, String description, int calories);

        void updateMeal(int id, LocalDateTime localDateTime, String description, int calories);

        void deleteMeal(int id);



}
