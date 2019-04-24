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

@WebServlet("/register")
public class UserRegistrationController extends HttpServlet {
    private UserDao dao = UserDaoImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/list").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long targetId = Long.parseLong(request.getParameter("id"));
        Optional<User> user = dao.findUserById(targetId);

        String name = request.getParameter("name");
        Integer age = Integer.valueOf(request.getParameter("age"));
        Integer salary = Integer.valueOf(request.getParameter("salary"));

        User newUser = new User()
                .setName(name)
                .setAge(age)
                .setSalary(salary);

        if (!user.isPresent()){
            dao.saveUser(newUser);
        }
        else {
            newUser.setId(targetId);
            dao.updateUser(newUser);
        }
        response.sendRedirect(request.getContextPath() + "/list");
    }
}
