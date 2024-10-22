package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepository implements MealRepository {

    JdbcTemplate template;

    public JdbcMealRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Meal save(Meal meal, int userId) {

        String sql = "INSERT INTO meals(date, description, calories, user_id) VALUES (?, ?, ?, ?)";
        template.update(sql, meal.getDateTime(), meal.getDescription(), meal.getCalories(), userId);
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        String sql = "DELETE FROM meals WHERE id = ?";
        template.update(sql, id);
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        String sql = "SELECT * FROM meals WHERE id = ?";

        return template.query(sql, new Object[]{id}, new MealMapper()).stream().findFirst().get();
    }

    @Override
    public List<Meal> getAll(int userId) {
        String sql = "SELECT * FROM meals Order By date DESC";

        return template.query(sql, new MealMapper());
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        String sql = "SELECT * FROM meals WHERE date > ? AND date < ?";
        template.query(sql, new Object[]{startDateTime, endDateTime}, new MealMapper());
        return null;
    }
}
