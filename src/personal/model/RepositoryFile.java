package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line));
        }
        return users;
    }

    @Override
    public String createUser(User user) {
        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        usersToFile(users);
        return id;
    }
    /**
     * Обновление данных заданного юзера. При обновлении - если новые данные пустые, оставляем старые данные.
     */
    @Override
    public void updateUser(User fUser) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId().equals(fUser.getId())) {
                if (fUser.getFirstName() != "") {
                    user.setFirstName(fUser.getFirstName());    
                } 
                if (fUser.getLastName() != "") {
                    user.setLastName(fUser.getLastName());    
                }
                if (fUser.getPhone() != "") {
                    user.setPhone(fUser.getPhone());    
                }
            }
        }        
        usersToFile(users);
    }

    private void usersToFile(List<User> newusers){
        List<String> lines = new ArrayList<>();
        for (User item: newusers) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }
}
