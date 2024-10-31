package com.bxrbasov.app.servlet.User;

import com.bxrbasov.app.dto.User.LoginUserDto;
import com.bxrbasov.app.dto.User.UserDto;
import com.bxrbasov.app.exception.ValidationException;
import com.bxrbasov.app.service.UserService;
import com.bxrbasov.app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("User/login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginUserDto build = LoginUserDto.builder()
                .user_surname(req.getParameter("user_surname"))
                .user_password(req.getParameter("user_password"))
                .build();
        try {
            UserDto userDto = userService.loginUser(build);
            req.getSession().setAttribute("user", userDto);
            req.getSession().setMaxInactiveInterval(1800);
            resp.sendRedirect("/nebank/profile");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrorList());
            doGet(req, resp);
        }
    }

}
