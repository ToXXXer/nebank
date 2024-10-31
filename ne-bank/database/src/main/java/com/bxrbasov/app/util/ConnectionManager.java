package com.bxrbasov.app.util;

import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static String URL_USERS = "db.url.users";
    private static String URL_WALLETS = "db.url.wallets";
    private static String URL_CONTACTS = "db.url.contacts";
    private static String USERNAME = "db.user";
    private static String PASSWORD = "db.password";
    private static String DRIVER = "db.driver";

    static {
        loadDriver();
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName(PropertiesUtil.get(DRIVER));
    }

    @SneakyThrows
    public static Connection openUsers() {
        return DriverManager.getConnection(PropertiesUtil.get(URL_USERS), PropertiesUtil.get(USERNAME), PropertiesUtil.get(PASSWORD));
    }

    @SneakyThrows
    public static Connection openWallets() {
        return DriverManager.getConnection(PropertiesUtil.get(URL_WALLETS), PropertiesUtil.get(USERNAME), PropertiesUtil.get(PASSWORD));
    }

    @SneakyThrows
    public static Connection openContacts() {
        return DriverManager.getConnection(PropertiesUtil.get(URL_CONTACTS), PropertiesUtil.get(USERNAME),PropertiesUtil.get(PASSWORD));
    }

}
