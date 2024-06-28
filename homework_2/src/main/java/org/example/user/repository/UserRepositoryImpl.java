package org.example.user.repository;

import lombok.Getter;
import org.example.database.DbConnection;
import org.example.out.Output;
import org.example.user.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Getter
public class UserRepositoryImpl implements UserRepository {

    @Override
    public void saveUser(String userName, String password) {
        if (!userName.isEmpty() && !password.isEmpty()) {
            String newUserInsert = "INSERT INTO data.users (user_name, password) VALUES (?, ?)";
            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(newUserInsert)) {
                connection.setAutoCommit(false);
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();
                connection.commit();
                Output.printMessage(String.format("Пользователь \"%s\" зарегистрирован%n", userName));
            } catch (SQLException e) {
                System.out.println("Got SQL Exception in transaction " + e.getMessage());
            }
        } else {
            Output.printMessage(String.format("Пользователь \"%s\" уже зарегистрирован, выберете другое имя%n", userName));
        }
    }

    @Override
    public Optional<User> findUserByCredentials(String userName, String password) {
        String findByCredentials = "SELECT * FROM data.users WHERE user_name = ? AND password = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findByCredentials)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt("user_id"))
                        .userName(resultSet.getString("user_name"))
                        .password(resultSet.getString("password"))
                        .build();
                return Optional.of(user);
            }
        } catch (SQLException e) {
            System.out.println("Got SQL Exception in transaction " + e.getMessage());
        }
        return Optional.empty();
    }

    //@Override
    //public boolean checkUserCredentials(String userName, String password) {
    //    return true; //registeredUser.containsKey(userName) && registeredUser.get(userName).equals(password);
//
    //}
}