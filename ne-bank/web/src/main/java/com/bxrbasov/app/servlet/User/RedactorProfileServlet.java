package com.bxrbasov.app.servlet.User;

import com.bxrbasov.app.dto.User.ProfileRedactorDto;
import com.bxrbasov.app.dto.User.UserDto;
import com.bxrbasov.app.exception.ValidationException;
import com.bxrbasov.app.service.ImageService;
import com.bxrbasov.app.service.UserService;
import com.bxrbasov.app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/redactor")
public class RedactorProfileServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();
    private ImageService imageService = ImageService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("User/profileRedactor")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        Part user_image = req.getPart("user_image");
        String name = user_image.getSubmittedFileName();
        ProfileRedactorDto redact = ProfileRedactorDto.builder()
                .user_id(user.getUser_id())
                .user_surname(req.getParameter("user_surname") == null || req.getParameter("user_surname").equals("") ? user.getUser_surname() : req.getParameter("user_surname"))
                .user_password(req.getParameter("user_password") == null || req.getParameter("user_password").equals("") ? "" : req.getParameter("user_password"))
                .user_password_confirm(req.getParameter("user_password_confirm") == null || req.getParameter("user_password_confirm").equals("") ? "" : req.getParameter("user_password_confirm"))
                .user_phone_number(req.getParameter("user_phone_number") == null || req.getParameter("user_phone_number").equals("") ? user.getUser_phone_number() : req.getParameter("user_phone_number"))
                .user_email(req.getParameter("user_email") == null || req.getParameter("user_email").equals("") ? user.getUser_email() : req.getParameter("user_email"))
                .user_privilege(req.getParameter("user_privilege") == null || req.getParameter("user_privilege").equals("") ? user.getUser_privilege() : req.getParameter("user_privilege"))
                .user_last_name(req.getParameter("user_last_name") == null || req.getParameter("user_last_name").equals("") ? user.getUser_last_name() : req.getParameter("user_last_name"))
                .user_patronymic_name(req.getParameter("user_patronymic_name") == null || req.getParameter("user_patronymic_name").equals("") ? user.getUser_patronymic_name() : req.getParameter("user_patronymic_name"))
                .user_first_name(req.getParameter("user_first_name") == null || req.getParameter("user_first_name").equals("") ? user.getUser_first_name() : req.getParameter("user_first_name"))
                .user_corporation_name(req.getParameter("user_corporation_name") == null || req.getParameter("user_corporation_name").equals("") ? user.getUser_corporation_name() : req.getParameter("user_corporation_name"))
                .user_image(name.equals("") ? user.getUser_image() : user.getUser_id().toString())
                .build();
        try {
            UserDto userDto = userService.redactorUser(redact);
            imageService.saveImage(user_image, userDto.getUser_id());
            req.getSession().invalidate();
            req.getSession().setAttribute("user", userDto);
            resp.sendRedirect("/nebank/profile");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrorList());
            doGet(req, resp);
        }
    }
}
