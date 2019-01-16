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

    int updateUserNameRole(String username, Roles.Role role, long userId);

    int updateUserNameRoleStatus(String username, Roles.Role role, Statuses.Status status, long userId);

    /*
     * The password field must have a separate method in our implementation
     * As it is not the password that is retrieved from the db
     * but the MD5 hash of the password
     */
    int updateUserPassword(String password, long userId);

    int deleteUser(long userId);

    int deleteUser(User user);
}
