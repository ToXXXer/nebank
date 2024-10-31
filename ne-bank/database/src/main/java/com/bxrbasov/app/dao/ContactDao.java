package com.bxrbasov.app.dao;

import com.bxrbasov.app.entity.Contact;
import com.bxrbasov.app.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactDao implements Dao<Contact, Integer> {
    private static UserDao userDao = UserDao.getInstance();
    private static ContactDao instance = new ContactDao();

    private static final String GET_ALL_BY_USER_ID = """
            SELECT contact_id, first_user, second_user FROM contacts WHERE second_user = ?
            UNION 
            SELECT contact_id, second_user, first_user FROM contacts WHERE first_user = ?
            """;

    @Override
    public Optional<Integer> get(Contact key) {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> update(Integer value) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Contact key) {
        return false;
    }

    @Override
    public Optional<Integer> save(Integer value) {
        return Optional.empty();
    }

    @Override
    public List<Integer> getAll() {
        return List.of();
    }

    @SneakyThrows
    public List<Contact> getAllByUserId(Integer id) {
        List<Contact> contacts = new ArrayList<>();
        Connection connection = ConnectionManager.openContacts();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BY_USER_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            contacts.add(buildContact(resultSet));
        }
        return contacts;
    }

    @SneakyThrows
    private Contact buildContact(ResultSet resultSet) {
        return Contact.builder()
                .contact_id(resultSet.getObject(1, Integer.class))
                .first_user(userDao.get(resultSet.getObject(2, Integer.class)).get())
                .second_user(userDao.get(resultSet.getObject(3, Integer.class)).get())
                .build();
    }

    public static ContactDao getInstance() {
        return instance;
    }
}
