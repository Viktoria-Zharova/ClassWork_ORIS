package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import java.util.UUID;

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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session_uuid")) {
                    String uuidString = cookie.getValue();
                    UUID uuid = UUID.fromString(uuidString);

                    try {
                        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                        PreparedStatement statement = connection.prepareStatement("SELECT user_id FROM session_data WHERE uuid = ?");
                        statement.setObject(1, uuid);
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                            response.sendRedirect("users");
                            return;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
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
            request.getSession().setAttribute("user", user.get());
            UUID uuid = UUID.randomUUID();
            Cookie cookie = new Cookie("session_uuid", uuid.toString());
            response.addCookie(cookie);
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO session_data (user_id, uuid) VALUES (?, ?)");
                statement.setLong(1, user.get().getId());
                statement.setObject(2, uuid);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

}
