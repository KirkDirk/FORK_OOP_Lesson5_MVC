package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.createUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    /**
     * Получаем список всех пользователей
     * @return - возвращает List<User>
     */
    public List<User> getListUsers(){
        return repository.getAllUsers();
    }
    /**
     * Проводим замену пользователя в репе
     * @param user
     */
    public void updateUser(User user){
        repository.updateUser(user);
    }
}
