package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    String createUser(User user);
    
    /**
     * Обновление данных пользователя по ID
     * @param user - пользователь для обновления
     */
    void updateUser(User user);
    
    /**
     * Удаление заданного по ID пользователя
     * @param deleteID - ID удаляемого пользователя
     */
    void deleteUser(String deleteID);
}
