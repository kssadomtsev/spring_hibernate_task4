package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Tiguan", 113)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("X3", 209)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Samara", 15)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("X-Trail", 55)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        User user1 = userService.getUserByCar("Tiguan", 113);
        System.out.println(user1);

        User user2 = userService.getUserByCar("Tiguan", 123);
        System.out.println(user2);

        User user3 = userService.getUserByCar("Samara", 15);
        System.out.println(user3);

        context.close();
    }
}
