package com.mateacademy.controller;

import com.mateacademy.dao.UserDao;
import com.mateacademy.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteController extends HttpServlet {
    private UserDao dao = UserDaoImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("id");
        if (userId == null) {
            request.getRequestDispatcher("/list").forward(request, response);
        }
        else {
            Long id = Long.parseLong(userId);
            dao.deleteUser(id);
            response.sendRedirect(request.getContextPath() + "/list");
        }
    }
}
