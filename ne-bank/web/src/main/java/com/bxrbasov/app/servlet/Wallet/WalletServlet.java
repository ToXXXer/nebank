package com.bxrbasov.app.servlet.Wallet;

import com.bxrbasov.app.dto.Wallet.TransactionDto;
import com.bxrbasov.app.dto.Wallet.WalletDto;
import com.bxrbasov.app.service.TransactionService;
import com.bxrbasov.app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/wallet")
public class WalletServlet extends HttpServlet {
    private static TransactionService transactionService = TransactionService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WalletDto wallet = (WalletDto) req.getSession().getAttribute("wallet");
        List<TransactionDto> transactions = transactionService.getAllByWalletId(wallet.getWallet_id());
        req.setAttribute("transactions", transactions);
        req.getRequestDispatcher(JspHelper.getPath("Wallet/wallet")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String transaction_type = req.getParameter("transaction_type");
        switch (transaction_type) {
            case "deposit":
                resp.sendRedirect("/nebank/depositwallet");
                break;
            case "replenishment":
                resp.sendRedirect("/nebank/replenishmentwallet");
                break;
            case "delete":
                resp.sendRedirect("/nebank/deletewallet");
                break;
                default:
                    resp.sendRedirect("/nebank/main");
                    break;
        }
    }
}
