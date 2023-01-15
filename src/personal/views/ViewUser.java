package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    userController.saveUser(getUserData());
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(id);
                        System.out.println(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    System.out.println("Все пользователи: ");
                    List<User> outList = userController.getListUsers();
                    for (User user : outList) {
                        System.out.println(user.toString());
                    }
                    break;
                case UPDATE:
                    String findID = prompt("Идентификатор пользователя:");
                    User user = getUserData();
                    user.setId(findID);
                    userController.updateUser(user);
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    /**
     * Метод сбора данных пользователя с консоли
     * @return - User с полями ИМЯ, ФАМИЛИЯ, ТЕЛЕФОН
     */
    private User getUserData(){
        User tempUser = new User(null, null, null);
        tempUser.setFirstName(prompt("Имя: "));
        tempUser.setLastName(prompt("Фамилия: "));
        tempUser.setPhone(prompt("Номер телефона: "));
        return tempUser;
    }
}
