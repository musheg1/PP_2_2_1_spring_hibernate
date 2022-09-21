package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainApp {

   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      User user = new User("User1", "Lastname1", "user1@mail.ru", new Car(1, "LADA"));
      userService.add(user);
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car(2, "HYUNDAI")));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car(3, "TOYOTA")));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car(4, "BMW")));

      System.out.println("Все пользователи:\n");
      List<User> users = userService.listUsers();
      users.forEach(System.out::println);

      Car car = new Car(4, "BMW");
      System.out.println("Пользователи с машиной:\n" + car + "\n");
      carService.listUsers(car).forEach(System.out::println);

      context.close();
   }
}
