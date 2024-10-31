package com.bxrbasov.app.dao;

import com.bxrbasov.app.entity.Wallet;
import com.bxrbasov.app.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WalletDao implements Dao<Integer, Wallet> {
    private static final WalletDao instance = new WalletDao();

    private static final String SAVE = """
            INSERT INTO wallets(wallet_name, wallet_purpose, wallet_comment, user_id)
            VALUES (?, ?, ?, ?)
            """;
    private static final String GET_ALL_BY_USER_ID = """
            SELECT * FROM wallets WHERE user_id = ?
            """;
    private static final String GET = """
            SELECT * FROM wallets WHERE wallet_id = ?
            """;
    private static final String DELETE = """
            DELETE FROM wallets WHERE wallet_id = ?
            """;
    private static final String UPDATE = """
            UPDATE wallets SET amount_money = ? WHERE wallet_id = ?
            """;

    @SneakyThrows
    @Override
    public Optional<Wallet> get(Integer key) {
        Wallet wallet = null;
        Connection connection = ConnectionManager.openWallets();
        PreparedStatement preparedStatement = connection.prepareStatement(GET);
        preparedStatement.setInt(1, key);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            wallet = buildWallet(resultSet);
        }
        return Optional.ofNullable(wallet);
    }

    @SneakyThrows
    @Override
    public Optional<Wallet> update(Wallet value) {
        Connection connection = ConnectionManager.openWallets();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, value.getAmount_money());
        preparedStatement.setInt(2, value.getWallet_id());
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        return Optional.of(buildWallet(generatedKeys));
    }

    @SneakyThrows
    @Override
    public boolean delete(Integer key) {
        Connection connection = ConnectionManager.openWallets();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        preparedStatement.setInt(1, key);
        preparedStatement.executeUpdate();
        return true;
    }

    @SneakyThrows
    @Override
    public Optional<Wallet> save(Wallet value) {
        Connection connection = ConnectionManager.openWallets();
        Wallet wallet = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, value.getWallet_name());
        preparedStatement.setString(2, value.getWallet_purpose());
        preparedStatement.setString(3, value.getWallet_comment());
        preparedStatement.setInt(4, value.getUser_id());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()) {
            wallet = buildWallet(resultSet);
        }
        return Optional.ofNullable(wallet);
    }

    @Override
    public List<Wallet> getAll() {
        return List.of();
    }

    @SneakyThrows
    public List<Wallet> getAllByUserId(Integer user_id) {
        List<Wallet> walletList = new ArrayList<>();
        Connection connection = ConnectionManager.openWallets();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BY_USER_ID);
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            walletList.add(buildWallet(resultSet));
        }
        return walletList;
    }

    @SneakyThrows
    private Wallet buildWallet(ResultSet resultSet) {
        return Wallet.builder()
                .wallet_id(resultSet.getObject("wallet_id", Integer.class))
                .wallet_name(resultSet.getObject("wallet_name", String.class))
                .wallet_purpose(resultSet.getObject("wallet_purpose", String.class))
                .wallet_comment(resultSet.getObject("wallet_comment", String.class))
                .amount_money(resultSet.getObject("amount_money", Long.class))
                .user_id(resultSet.getObject("user_id", Integer.class))
                .build();
    }

    public static WalletDao getInstance() {
        return instance;
    }
}
