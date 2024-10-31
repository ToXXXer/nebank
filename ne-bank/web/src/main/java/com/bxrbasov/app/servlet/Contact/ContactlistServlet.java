package com.bxrbasov.app.servlet.Contact;

import com.bxrbasov.app.dto.Contact.ContactDto;
import com.bxrbasov.app.dto.User.UserDto;
import com.bxrbasov.app.dto.Wallet.WalletDto;
import com.bxrbasov.app.exception.ValidationException;
import com.bxrbasov.app.service.ContactService;
import com.bxrbasov.app.service.WalletService;
import com.bxrbasov.app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/contactlist")
public class ContactlistServlet extends HttpServlet {
    private static final ContactService contactService = ContactService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        List<ContactDto> contactList = contactService.getAllByUserId(user.getUser_id());
        req.setAttribute("contactList", contactList);
        req.getRequestDispatcher(JspHelper.getPath("Contact/contactlist")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_id = req.getParameter("user_id");
//        try {
//            WalletDto wallet = walletService.getWallet(Integer.parseInt(wallet_id));
//            req.getSession().setAttribute("wallet", wallet);
//            resp.sendRedirect("/nebank/wallet");
//        } catch (ValidationException exception) {
//            req.setAttribute("errors", exception.getErrorList());
//            doGet(req, resp);
//        }
    }
}
