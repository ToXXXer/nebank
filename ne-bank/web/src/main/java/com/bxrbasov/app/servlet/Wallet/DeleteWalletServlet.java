package com.bxrbasov.app.servlet.Wallet;

import com.bxrbasov.app.dto.User.UserDto;
import com.bxrbasov.app.dto.Wallet.CreateWalletDto;
import com.bxrbasov.app.dto.Wallet.WalletDto;
import com.bxrbasov.app.exception.ValidationException;
import com.bxrbasov.app.service.WalletService;
import com.bxrbasov.app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletewallet")
public class DeleteWalletServlet extends HttpServlet {
    private static final WalletService walletService = WalletService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("Wallet/deletewallet")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WalletDto wallet = (WalletDto) req.getSession().getAttribute("wallet");
        req.getSession().removeAttribute("wallet");
        try {
            walletService.deleteWallet(wallet);
            resp.sendRedirect("/nebank/walletlist");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrorList());
            doGet(req, resp);
        }
    }
}
