package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;
       
    private FileOperation fileOperation2;


    public RepositoryFile(FileOperation fileOperation, FileOperation fileOperation2) {
        this.fileOperation = fileOperation;
        this.fileOperation2 = fileOperation2;
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
            if (max < id) {
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
     * Обновление данных заданного юзера. При обновлении - если новые данные пустые,
     * оставляем старые данные.
     * @param fUser - данные заданного пользователя, которые корректируются. Если
     *              значение не вводить, поле останется прежним
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

    /**
     * Метод записи данных пользователей в файл users.txt
     * Дублируем запись в users2.txt через UserMapper.map2
     * 
     * @param newusers - список пользователей для записи
     */
    private void usersToFile(List<User> newusers) {
        List<String> lines = new ArrayList<>();
        List<String> lines2 = new ArrayList<>();
        for (User item : newusers) {
            lines.add(mapper.map(item));
            lines2.add(mapper.map2(item));
        }
        fileOperation.saveAllLines(lines);
        fileOperation2.saveAllLines(lines2);
    }
    
    /**
     * Метод удаления по ID записи пользователя в списке
     */
    @Override
    public void deleteUser(String deleteID) {
        List<User> users = getAllUsers();
        int index = -1;
        for (User u : users) {
            if (u.getId().equals(deleteID)) {
                index = users.indexOf(u);
            }
        }
        users.remove(index);
        usersToFile(users);
    }
}
