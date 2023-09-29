package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@WebServlet("/login")
public class AuthenticationServlet extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Ktcrjdf65";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test_db";
    private UsersRepository usersRepository;
    @Override
    public void init() throws ServletException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            usersRepository = new UsersRepositoryJdbcImpl(connection, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> user;
        user = usersRepository.findByLogin(User.builder()
                .username(username)
                .password(password)
                .build());

        if (user.isPresent()) {
            // Successful login, redirect to a user-specific page
            request.getSession().setAttribute("user", user.get());
            response.sendRedirect("users");
        } else {
            // Failed login, redirect back to the login page with an error message
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
