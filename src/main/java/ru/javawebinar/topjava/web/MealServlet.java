package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.InMemoryMealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(UserServlet.class);
    final static int caloriesPerDay = 2000;
    private MealDao mealDao;

    public void init() throws ServletException {
        mealDao = new InMemoryMealDao();
        log.info("MealServlet initialized with MealDao: {}", mealDao.getClass().getName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        log.debug("doGet called with action: {}", action);

        if (action == null) {
            List<MealTo> mealTos = MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.MIN,  LocalTime.MAX, caloriesPerDay );
            req.setAttribute("meals", mealTos);
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            mealDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/meals");
        } else if (action.equals("update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Meal meal = mealDao.findById(id);
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("/mealsUpdate.jsp").forward(req, resp);
        } else if (action.equals("add")) {
            req.getRequestDispatcher("/mealsAdd.jsp").forward(req, resp);
        } else {
            List<MealTo> mealTos = MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.MIN,  LocalTime.MAX, caloriesPerDay );
            req.setAttribute("meals", mealTos);
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        log.debug("doPost called with action: {}", action);
        String dateTimeString = req.getParameter("date");
        //DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        if (action == null) {
            List<MealTo> mealTos = MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.MIN,  LocalTime.MAX, caloriesPerDay );
            req.setAttribute("meals", mealTos);
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else if (action.equals("add")) {
            int id = mealDao.increaseId();
            mealDao.add(new Meal (id, dateTime, description, calories));
            resp.sendRedirect(req.getContextPath() + "/meals");
        } else if (action.equals("update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            mealDao.update(new Meal (id, dateTime, description, calories));
            resp.sendRedirect(req.getContextPath()+ "/meals");
        } else {
            List<MealTo> mealTos = MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.MIN,  LocalTime.MAX, caloriesPerDay );
            req.setAttribute("meals", mealTos);
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }
    }
}
