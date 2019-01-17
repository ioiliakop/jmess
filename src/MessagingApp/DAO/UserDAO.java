package MessagingApp.DAO;

import MessagingApp.Entities.Roles;
import MessagingApp.Entities.Roles.Role;
import MessagingApp.Entities.Statuses;
import MessagingApp.Entities.User;

import java.util.List;

public interface UserDAO {

    User getUser(long userId);

    User getUser(String username);

    /* used for authentication at login screen */
    User getActiveUserByUsernameAndPassword(String username, String password);

    List<User> getAllUsers();

    List<User> getAllActiveUsers();

    long insertUser(String username, String password, Role role);

    /* default role and status are set by the db by default, values USER and ACTIVE respectively */
    long insertUser(String username, String password);

    int updateUser(User user);

    int deleteUser(User user);
}
