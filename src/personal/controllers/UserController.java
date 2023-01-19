package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {
        validationUser(user);
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
     * 
     * @return - возвращает List<User>
     */
    public List<User> getListUsers() {
        return repository.getAllUsers();
    }

    /**
     * Проводим замену пользователя в репе
     * 
     * @param user
     * @throws Exception
     */
    public void updateUser(User user) throws Exception {
        validIdUpdate(user.getId());
        validationID(user);
        repository.updateUser(user);
    }

    /**
     * Проверка на отстутствие имени или фамилии при вводе данных
     * 
     * @param user
     */
    private void validationUser(User user) throws Exception {
        if (user.getFirstName().contains(" ") || user.getLastName().contains(" "))
            throw new Exception("The name cannot contain spaces");
    }

    /**
     * Проверка на пустотность ID
     * @param user - передаваемый пользователья для проверки
     * @throws Exception
     */
    private void validationID (User user) throws Exception {
        if (user.getId().isEmpty())
            throw new Exception("ID is empty");
        validationID(user);
    }

    /**
     * Проверка при UPDATE на существование ID
     * @param inputID - введенный ID
     * @throws Exception
     */
    public void validIdUpdate (String inputID) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User us : users) {
            if (us.getId().equals(inputID)) { return; }
        }
        throw new Exception("ID not found");
    }

    /**
     * Удаление пользователя по ID
     * @param deleteID - передаваемый ID для удаления
     */
    public void deleteUser (String deleteID) {
        repository.deleteUser(deleteID);
    }

}
