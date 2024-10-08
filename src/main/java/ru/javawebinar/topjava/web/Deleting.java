package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


    public class Deleting extends HttpServlet {
        MealDAO mealDAO = new MealDAOImp();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int id = Integer.parseInt(req.getParameter("id"));
            mealDAO.deleteMeal(id);
            System.out.println(id);
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



//            mealDAO.addMeal(dateTime, description, calories);
//            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

