package com.bxrbasov.app.servlet.Contact;

import com.bxrbasov.app.dto.User.UserDto;
import com.bxrbasov.app.service.UserService;
import com.bxrbasov.app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/createcontact")
public class CreateContactServlet extends HttpServlet {
    private static UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("Contact/createcontact")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_surname = req.getParameter("user_surname");
        if(user_surname == null || user_surname.isEmpty()){
            doGet(req, resp);
        }
        UserDto second_user = (UserDto) req.getSession().getAttribute("user");
        UserDto first_user = userService.getUserBySurname(user_surname);
    }
}
