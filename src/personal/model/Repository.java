package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    String createUser(User user);
    void updateUser(User user);
    void deleteUser(String deleteID);
}
