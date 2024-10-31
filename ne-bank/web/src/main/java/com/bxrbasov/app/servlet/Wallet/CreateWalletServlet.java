package com.bxrbasov.app.servlet.Wallet;

import com.bxrbasov.app.dto.User.UserDto;
import com.bxrbasov.app.dto.Wallet.CreateWalletDto;
import com.bxrbasov.app.exception.ValidationException;
import com.bxrbasov.app.service.WalletService;
import com.bxrbasov.app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createwallet")
public class CreateWalletServlet extends HttpServlet {
    private static final WalletService walletService = WalletService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("Wallet/createwallet")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        CreateWalletDto create = CreateWalletDto.builder()
                .wallet_name(req.getParameter("wallet_name") == null || req.getParameter("wallet_name").equals("") ? "Кошелёк" : req.getParameter("wallet_name"))
                .wallet_purpose(req.getParameter("wallet_purpose") == null || req.getParameter("wallet_purpose").equals("") ? "Накопительный" : req.getParameter("wallet_purpose"))
                .wallet_comment(req.getParameter("wallet_comment") == null || req.getParameter("wallet_comment").equals("") ? "Нет записи" : req.getParameter("wallet_comment"))
                .user_id(user.getUser_id())
                .build();
        try {
            walletService.createWallet(create);
            resp.sendRedirect("/nebank/walletlist");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrorList());
            doGet(req, resp);
        }
    }
}
