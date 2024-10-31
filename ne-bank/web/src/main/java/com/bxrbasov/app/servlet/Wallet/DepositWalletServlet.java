package com.bxrbasov.app.servlet.Wallet;

import com.bxrbasov.app.dto.Wallet.CreateTransactionDto;
import com.bxrbasov.app.dto.Wallet.WalletDto;
import com.bxrbasov.app.exception.ValidationException;
import com.bxrbasov.app.service.TransactionService;
import com.bxrbasov.app.service.WalletService;
import com.bxrbasov.app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/depositwallet")
public class DepositWalletServlet extends HttpServlet {
    private static final WalletService walletService = WalletService.getInstance();
    private static final TransactionService transactionService = TransactionService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("Wallet/depositwallet")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String amountMoney = req.getParameter("amount_money");
        if (amountMoney == null || amountMoney.isEmpty()) {
            doGet(req, resp);
        }
        WalletDto walletDto = (WalletDto) req.getSession().getAttribute("wallet");
        CreateTransactionDto createTransactionDto = CreateTransactionDto.builder()
                .wallet_id_from(walletDto.getWallet_id())
                .wallet_id_to(walletDto.getWallet_id())
                .transaction_type("deposit")
                .amount_money(Long.valueOf(req.getParameter("amount_money")))
                .build();
        try {
            transactionService.saveTransaction(createTransactionDto);
            WalletDto wallet = walletService.addAmountMoney(walletDto.getWallet_id(), createTransactionDto.getAmount_money());
            req.getSession().setAttribute("wallet", wallet);
            resp.sendRedirect("/nebank/wallet");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrorList());
            doGet(req, resp);
        }
    }
}
