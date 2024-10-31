package com.bxrbasov.app.servlet.User;

import com.bxrbasov.app.dto.User.RegistrationUserDto;
import com.bxrbasov.app.exception.ValidationException;
import com.bxrbasov.app.service.UserService;
import com.bxrbasov.app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object privilege = req.getSession().getAttribute("user_privilege");
        if(privilege == null) {
            req.getRequestDispatcher(JspHelper.getPath("main")).forward(req, resp);
        } else {
            req.getSession().setAttribute("user_privilege", privilege);
            req.getRequestDispatcher(JspHelper.getPath("User/registration")).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationUserDto build = RegistrationUserDto.builder()
                .user_surname(req.getParameter("user_surname"))
                .user_email(req.getParameter("user_email"))
                .user_password(req.getParameter("user_password"))
                .user_password_confirm(req.getParameter("user_password_confirm"))
                .user_corporation_name(req.getParameter("user_corporation_name"))
                .user_phone_number(req.getParameter("user_phone_number"))
                .user_privilege(req.getSession().getAttribute("user_privilege").toString())
                .user_first_name(req.getParameter("user_first_name"))
                .user_last_name(req.getParameter("user_last_name"))
                .user_patronymic_name(req.getParameter("user_patronymic_name"))
                .build();
        try {
            userService.registrationUser(build);
            resp.sendRedirect("/nebank/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrorList());
            doGet(req, resp);
        }
    }
}
