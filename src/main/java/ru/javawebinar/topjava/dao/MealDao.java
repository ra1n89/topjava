package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface MealDao {

        void add(Meal meal);

        void update(int index, Meal meal);

        void delete(int id);

        public Meal findById(int id);

        public List<Meal> getMeals();

        public int increaseId();
}
