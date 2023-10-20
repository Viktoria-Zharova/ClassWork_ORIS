package repository;

import models.User;
import repository.UsersRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    private Statement statement;

    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from driver";
    private static final String SQL_INSERT_INTO_USERS = "insert into driver(username,password,first_name,last_name, age) values ";

    public UsersRepositoryJdbcImpl(Connection connection, Statement statement) {
        this.statement = statement;
        this.connection = connection;
    }
    @Override
    public List findAllByAge() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from driver");
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .secondName(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .username(resultSet.getString("username"))
                        .build();
                result.add(user);
//                if (user.getAge().equals(age)) {
//                    result.add(user);
//                }
            }
            return result;
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public void save(User entity) {
        String sql = SQL_INSERT_INTO_USERS + "('" + entity.getUsername() + "', '" + entity.getPassword() + "', '" + entity.getFirstName() + "', '" + entity.getSecondName() + "', '" + entity.getAge() + "');";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByLogin(User login) {
        try {
            String username = login.getUsername();
            String password = login.getPassword();

            String sql = "SELECT * FROM driver WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .secondName(resultSet.getString("last_name"))
                        .build();
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return Optional.empty(); // Return empty if user is not found
    }
}