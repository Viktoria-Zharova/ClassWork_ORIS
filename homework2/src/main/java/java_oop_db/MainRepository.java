package java_oop_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

public class MainRepository {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Ktcrjdf65";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test_db";

    public static void main(String[] args) throws Exception{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        UserRepository userRepository = new UserRepositoryJdbcImp(connection);
        Scanner scanner = new Scanner(System.in);

        // Testing findAllByAge
        System.out.print("Введи возраст поиска:");
        int age = scanner.nextInt();
        List<User> usersByAge = userRepository.findAllByAge(age);
        System.out.println("Users with age: " + age);
        for (User user : usersByAge) {
            System.out.println(user.getfirst_name() + " " + user.getlast_name());
        }
        System.out.print("Введи логин:");
        String login = scanner.next();
        // Testing findByusername
        List<User> usersByusername = userRepository.findByusername(login);
        System.out.printf("Users with username '%s':", login);
        for (User user : usersByusername) {
            System.out.println(user.getfirst_name() + " " + user.getlast_name());
        }
    }
}
