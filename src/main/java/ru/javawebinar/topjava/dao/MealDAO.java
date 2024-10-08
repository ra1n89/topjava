package ru.javawebinar.topjava.dao;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDAO {

        void addMeal(LocalDateTime localDateTime, String description, int calories);

        void updateMeal();

        void deleteMeal();



}
