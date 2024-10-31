package com.bxrbasov.app.dao;

import com.bxrbasov.app.entity.Transaction;
import com.bxrbasov.app.util.ConnectionManager;
import com.bxrbasov.app.util.LocalDateFormatter;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDao implements Dao<Integer, Transaction> {
    private static final TransactionDao instance = new TransactionDao();
    private static final String SAVE = """
            INSERT INTO transactions(transaction_type, wallet_id_from, wallet_id_to, amount_money)
            VALUES (?, ?, ?, ?)
            """;
    private static final String GET_ALL_BY_WAALLET_ID = """
            SELECT * FROM transactions WHERE wallet_id_from = ? ORDER BY transaction_date DESC
            """;
    @Override
    public Optional<Transaction> get(Integer key) {
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> update(Transaction value) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @SneakyThrows
    @Override
    public Optional<Transaction> save(Transaction value) {
        Connection connection = ConnectionManager.openWallets();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, value.getTransaction_type());
        preparedStatement.setInt(2, value.getWallet_id_from());
        preparedStatement.setInt(3, value.getWallet_id_to());
        preparedStatement.setDouble(4, value.getAmount_money());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return Optional.of(buildTransaction(resultSet));
    }

    @Override
    public List<Transaction> getAll() {
        return List.of();
    }

    @SneakyThrows
    public List<Transaction> getByWalletId(Integer wallet_id) {
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = ConnectionManager.openWallets();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BY_WAALLET_ID);
        preparedStatement.setInt(1, wallet_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            transactions.add(buildTransaction(resultSet));
        }
        return transactions;
    }

    @SneakyThrows
    private Transaction buildTransaction(ResultSet resultSet) {
        return Transaction.builder()
                .transaction_id(resultSet.getObject("transaction_id", Integer.class))
                .transaction_type(resultSet.getObject("transaction_type", String.class))
                .wallet_id_from(resultSet.getObject("wallet_id_from", Integer.class))
                .wallet_id_to(resultSet.getObject("wallet_id_to", Integer.class))
                .amount_money(resultSet.getObject("amount_money", Long.class))
                .transaction_date(LocalDateFormatter.format(resultSet.getTimestamp("transaction_date").toString()))
                .build();
    }

    public static TransactionDao getInstance() {
        return instance;
    }
}
