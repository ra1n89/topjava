package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {

    final static int caloriesPerDay = 2000;
    MealDao mealDao = new MealDaoInMemory();
    List<Meal> meals = mealDao.getMeals() ;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            List<MealTo> mealTos = MealsUtil.filteredByStreams(meals, LocalTime.of(0,0, 0),  LocalTime.of(23,59, 59),caloriesPerDay );
            System.out.println(meals);
            req.setAttribute("meals", mealTos);
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            mealDao.delete(id);
            resp.sendRedirect("/topjava/meals");
        } else if (action.equals("update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String dateTimeString = req.getParameter("date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

            String description = req.getParameter("description");

            int calories = Integer.parseInt(req.getParameter("calories"));
            req.setAttribute("id", id);
            req.setAttribute("date", dateTimeString);
            req.setAttribute("description", description);
            req.setAttribute("calories", calories);
            req.getRequestDispatcher("/mealsUpdate.jsp").forward(req, resp);
        } else if (action.equals("add")) {
            req.getRequestDispatcher("/mealsAdd.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action.equals("add")) {

            String dateTimeString = req.getParameter("date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

            String description = req.getParameter("description");

            int calories = Integer.parseInt(req.getParameter("calories"));
            int id = mealDao.increaseId();
            mealDao.add(new Meal (id, dateTime, description, calories));
            resp.sendRedirect("/topjava/meals");
        } else if (action.equals("update")) {

            int id = Integer.parseInt(req.getParameter("id"));
            String dateTimeString = req.getParameter("date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

            String description = req.getParameter("description");

            int calories = Integer.parseInt(req.getParameter("calories"));
            int index = meals.indexOf(mealDao.findById(id));
            mealDao.update(index, new Meal (id, dateTime, description, calories));
            resp.sendRedirect("/topjava/meals");
        }


    }
}
