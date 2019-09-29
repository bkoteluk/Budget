package pl.bogdank.budget.dao;

import pl.bogdank.budget.factory.ConnectionFactory;
import pl.bogdank.budget.model.Transaction;
import pl.bogdank.budget.model.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    private final static String CREATE = "INSERT INTO transactions(type, description, amount, date) VALUES(?, ?, ?, ?);";
    private final static String READ = "SELECT id, type, description, amount, date FROM transactions WHERE id=?;";
    private final static String READ_ALL_TYPE = "SELECT id, type, description, amount, date FROM transactions WHERE type=?;";
    private final static String READ_ALL = "SELECT id, type, description, amount, date FROM transactions WHERE type=?;";
    private final static String UPDATE = "UPDATE transactions SET type=?, description=?, amount=?, date=? WHERE id=?;";
    private final static String DELETE = "DELETE FROM transactions WHERE id=?;";

    private Connection connection = null;

    public TransactionDao() {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }

    public void create(Transaction transaction) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, transaction.getType().name());
            preparedStatement.setString(2,transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transaction read(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setDescription(resultSet.getString("description"));
                transaction.setDate(resultSet.getDate("date").toLocalDate());
                return transaction;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Transaction> readAll(TransactionType type) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_TYPE);
            preparedStatement.setString(1, type.name());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setDescription(resultSet.getString("description"));
                transaction.setDate(resultSet.getDate("date").toLocalDate());
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> readAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setDescription(resultSet.getString("description"));
                transaction.setDate(resultSet.getDate("date").toLocalDate());
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Transaction transaction) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,transaction.getType().name());
            preparedStatement.setString(2,transaction.getDescription());
            preparedStatement.setDouble(3,transaction.getAmount());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));
            preparedStatement.setInt(5, transaction.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
