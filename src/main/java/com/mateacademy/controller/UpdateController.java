package com.mateacademy.controller;

import com.mateacademy.dao.UserDao;
import com.mateacademy.dao.UserDaoImpl;
import com.mateacademy.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/update")
public class UpdateController extends HttpServlet {
    private UserDao dao = UserDaoImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<String> userId = Optional.ofNullable(request.getParameter("id"));
        if (!userId.isPresent())
            request.getRequestDispatcher("/list").forward(request, response);
        else {
            Long id = Long.parseLong(userId.get());
            User user = dao.findUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/list").forward(request, response);
        }
    }
}
