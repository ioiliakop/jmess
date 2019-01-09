package MessagingApp.DAO;

import MessagingApp.Entities.Constants.Roles;
import MessagingApp.Entities.User;

import java.util.List;

public interface UserDAO {

    User getUser(long id);

    User getUser(String username);

    User getUserByUsernameAndPassword(String username, String password);

    List<User> getAllUsers();

    long insertUser(String username, String password, Roles role);

    /* default role will be 1, simple user/viewer */
    long insertUser(String username, String password);

    int updateUser(String username, String password, Roles role, long id);

    int deleteUser(long id);
}
