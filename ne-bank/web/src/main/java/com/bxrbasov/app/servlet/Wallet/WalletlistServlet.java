package com.bxrbasov.app.servlet.Wallet;

import com.bxrbasov.app.dto.User.UserDto;
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
import java.util.List;

@WebServlet("/walletlist")
public class WalletlistServlet extends HttpServlet {
    private static final WalletService walletService = WalletService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        List<WalletDto> walletDtoList = walletService.getAllByUserId(user.getUser_id());
        Long sumMoney = 0L;
        for (WalletDto walletDto : walletDtoList) {
            sumMoney += walletDto.getAmount_money();
        }
        req.setAttribute("walletList", walletDtoList);
        req.setAttribute("sumMoney", sumMoney);
        req.getRequestDispatcher(JspHelper.getPath("Wallet/walletlist")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String wallet_id = req.getParameter("wallet_id");
        try {
            WalletDto wallet = walletService.getWallet(Integer.parseInt(wallet_id));
            req.getSession().setAttribute("wallet", wallet);
            resp.sendRedirect("/nebank/wallet");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrorList());
            doGet(req, resp);
        }
    }
}
