package com.bxrbasov.app.filter;

import com.bxrbasov.app.dto.User.UserDto;
import com.bxrbasov.app.dto.Wallet.WalletDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static com.bxrbasov.app.util.PathForFilter.*;

@WebFilter("/*")
public class AutorisationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, IMAGE, MAIN);
    private static final Set<String> PRIVATE_PATH = Set.of(PROFILE, REDACTOR, LOGOUT, WALLETLIST, CREATE_WALLET, CONTACTS, CREATE_CONTACT);
    private static final Set<String> PRIVATE_CHECK_ACCESS_PATH = Set.of(WALLET, DELETE_WALLET, DEPOSIT_WALLET, REPLENISHMENT_WALLET);
    private static final Set<String> ADMIN_PATH = Set.of(ADMINPAGE);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if(isPublicPath(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if(isPrivatePath(uri) && isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if(isPrivateCheckAccessPath(uri) && isUserLoggedIn(servletRequest)) {
            if(isWalletCorrect(servletRequest)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect("/nebank/walletlist");
            }
        } else if(isAdminPath(uri) && isUserLoggedIn(servletRequest) && isUserAdmin(servletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/nebank/main");
        }
    }

    private boolean isUserAdmin(ServletRequest servletRequest) {
        UserDto user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user.getUser_privilege() == "admin";
    }

    private boolean isAdminPath(String uri) {
        return ADMIN_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        UserDto user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPrivatePath(String uri) {
        return PRIVATE_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isPrivateCheckAccessPath(String uri) {
        return PRIVATE_CHECK_ACCESS_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isWalletCorrect(ServletRequest servletRequest) {
        UserDto user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        WalletDto wallet = (WalletDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("wallet");
        return wallet.getUser_id().equals(user.getUser_id());
    }

}
