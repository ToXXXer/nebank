package com.bxrbasov.app.dao;

import com.bxrbasov.app.entity.User;
import com.bxrbasov.app.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_USER = """
            INSERT INTO users(user_surname, user_password, user_phone_number, user_email, user_privilege,
                  user_last_name, user_patronymic_name, user_first_name, user_corporation_name) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)                                                         
            """;
    private static final String GET_ALL = """
            SELECT * FROM users
            """;
    private static final String GET_LOGIN_USER = """
            SELECT * FROM users WHERE user_surname = ? AND user_password = ?
            """;
    private static final String REDACT_USER = """
            UPDATE users SET user_surname = ?, user_password = ?, user_phone_number = ?, user_email = ?, user_privilege = ?,
                 user_last_name = ?, user_patronymic_name = ?, user_first_name = ?, user_corporation_name = ?, user_image = ?
            WHERE user_id = ?
            """;
    private static final String REDACT_USER_WITHOUT_PASSWORD = """
            UPDATE users SET user_surname = ?, user_phone_number = ?, user_email = ?, user_privilege = ?,
                 user_last_name = ?, user_patronymic_name = ?, user_first_name = ?, user_corporation_name = ?, user_image = ?
            WHERE user_id = ?
            """;
    private static final String GET_ALL_WTHOUT_USER_BY_ID = """
            SELECT * FROM users WHERE user_id != ?
            """;
    private static final String GET = """
            SELECT * FROM users WHERE user_id = ?
            """;
    private static final String GET_USER_BY_SURNAME = """
            SELECT * FROM users WHERE user_surname = ?
            """;

    @SneakyThrows
    @Override
    public Optional<User> get(Integer key) {
        Connection connection = ConnectionManager.openUsers();
        PreparedStatement preparedStatement = connection.prepareStatement(GET);
        preparedStatement.setInt(1, key);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return Optional.of(buildUser(resultSet));
    }

    @SneakyThrows
    @Override
    public Optional<User> update(User value) {
        return null;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @SneakyThrows
    @Override
    public Optional<User> save(User value) {
        Connection connection = ConnectionManager.openUsers();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, value.getUser_surname());
        preparedStatement.setString(2, value.getUser_password());
        preparedStatement.setString(3, value.getUser_phone_number());
        preparedStatement.setString(4, value.getUser_email());
        preparedStatement.setString(5, value.getUser_privilege());
        preparedStatement.setString(6, value.getUser_last_name());
        preparedStatement.setString(7, value.getUser_patronymic_name());
        preparedStatement.setString(8, value.getUser_first_name());
        preparedStatement.setString(9, value.getUser_corporation_name());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return Optional.of(buildUser(resultSet));
    }

    @SneakyThrows
    public Optional<User> getLoginUser(User loginUser) {
        Connection connection = ConnectionManager.openUsers();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LOGIN_USER);
        preparedStatement.setString(1, loginUser.getUser_surname());
        preparedStatement.setString(2, loginUser.getUser_password());
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = buildUser(resultSet);
        }
        return Optional.ofNullable(user);
    }

    @SneakyThrows
    public List<User> getAll() {
        Connection connection = ConnectionManager.openUsers();
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            users.add(buildUser(resultSet));
        }
        return users;
    }

    @SneakyThrows
    public Optional<User> redactUser(User user) {
        Connection connection = ConnectionManager.openUsers();
        User newUser = null;
        PreparedStatement preparedStatement = connection.prepareStatement(REDACT_USER, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getUser_surname());
        preparedStatement.setString(2, user.getUser_password());
        preparedStatement.setString(3, user.getUser_phone_number());
        preparedStatement.setString(4, user.getUser_email());
        preparedStatement.setString(5, user.getUser_privilege());
        preparedStatement.setString(6, user.getUser_last_name());
        preparedStatement.setString(7, user.getUser_patronymic_name());
        preparedStatement.setString(8, user.getUser_first_name());
        preparedStatement.setString(9, user.getUser_corporation_name());
        preparedStatement.setString(10, user.getUser_image());
        preparedStatement.setInt(11, user.getUser_id());
        preparedStatement.executeUpdate();
        ResultSet result = preparedStatement.getGeneratedKeys();
        if(result.next()) {
            newUser = buildUser(result);
        }
        return Optional.ofNullable(newUser);
    }

    @SneakyThrows
    public Optional<User> redactUserWithoutPassword(User user) {
        Connection connection = ConnectionManager.openUsers();
        User newUser = null;
        PreparedStatement preparedStatement = connection.prepareStatement(REDACT_USER_WITHOUT_PASSWORD, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getUser_surname());
        preparedStatement.setString(2, user.getUser_phone_number());
        preparedStatement.setString(3, user.getUser_email());
        preparedStatement.setString(4, user.getUser_privilege());
        preparedStatement.setString(5, user.getUser_last_name());
        preparedStatement.setString(6, user.getUser_patronymic_name());
        preparedStatement.setString(7, user.getUser_first_name());
        preparedStatement.setString(8, user.getUser_corporation_name());
        preparedStatement.setString(9, user.getUser_image());
        preparedStatement.setInt(10, user.getUser_id());
        preparedStatement.executeUpdate();
        ResultSet result = preparedStatement.getGeneratedKeys();
        if(result.next()) {
            newUser = buildUser(result);
        }
        return Optional.ofNullable(newUser);
    }

    @SneakyThrows
    public List<User> getAllWithoutUserWithId(Integer id) {
        Connection connection = ConnectionManager.openUsers();
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_WTHOUT_USER_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            users.add(buildUser(resultSet));
        }
        return users;
    }

    @SneakyThrows
    public User getUserBySurname(String userSurname) {
        Connection connection = ConnectionManager.openContacts();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_SURNAME);
        preparedStatement.setString(1, userSurname);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = buildUser(resultSet);
        }
        return user;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private static User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .user_id(resultSet.getObject("user_id", Integer.class))
                .user_surname(resultSet.getObject("user_surname", String.class))
                .user_password(resultSet.getObject("user_password", String.class))
                .user_phone_number(resultSet.getObject("user_phone_number", String.class))
                .user_email(resultSet.getObject("user_email", String.class))
                .user_privilege(resultSet.getObject("user_privilege", String.class))
                .user_last_name(resultSet.getObject("user_last_name", String.class))
                .user_patronymic_name(resultSet.getObject("user_patronymic_name", String.class))
                .user_first_name(resultSet.getObject("user_first_name", String.class))
                .user_corporation_name(resultSet.getObject("user_corporation_name", String.class))
                .user_image(resultSet.getObject("user_image", String.class))
                .build();
    }
}
