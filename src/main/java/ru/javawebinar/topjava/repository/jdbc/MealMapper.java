package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ru.javawebinar.topjava.model.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MealMapper implements RowMapper<Meal> {
    @Override
    public Meal mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        LocalDateTime localDateTime = rs.getTimestamp("date").toLocalDateTime();
        String description = rs.getString("description");
        int calories = rs.getInt("calories");

        Meal meal = new Meal(id, localDateTime, description, calories);

        return meal;
    }
}
