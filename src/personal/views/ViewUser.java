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

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT)
                return;
            try {
                switch (com) {
                    case CREATE:
                        try {
                            userController.saveUser(getUserData());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case READ:
                        String id = prompt("Идентификатор пользователя: ");

                        User user = userController.readUser(id);
                        System.out.println(user);

                        break;
                    case LIST:
                        System.out.println("Все пользователи: ");
                        List<User> outList = userController.getListUsers();
                        for (User usr : outList) {
                            System.out.println(usr.toString());
                        }
                        break;
                    case UPDATE:
                        String findID = prompt("Идентификатор пользователя:");
                        User findUser = getUserData();
                        findUser.setId(findID);
                        userController.updateUser(findUser);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage()); 
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
     * 
     * @return - User с полями ИМЯ, ФАМИЛИЯ, ТЕЛЕФОН
     */
    private User getUserData() {
        User tempUser = new User(null, null, null);
        tempUser.setFirstName(prompt("Имя: "));
        tempUser.setLastName(prompt("Фамилия: "));
        tempUser.setPhone(prompt("Номер телефона: "));
        return tempUser;
    }
}
