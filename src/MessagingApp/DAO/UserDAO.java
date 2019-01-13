package MessagingApp.DAO;

import MessagingApp.Entities.FinalEntities.Roles;
import MessagingApp.Entities.User;

import java.util.List;

import static MessagingApp.Entities.FinalEntities.*;

public interface UserDAO {

    User getUser(long userId);

    User getUser(String username);

    User getActiveUserByUsernameAndPassword(String username, String password);

    List<User> getAllUsers();

    List<User> getAllActiveUsers();

    long insertUser(String username, String password, Roles role);

    /* default role and status will be 1, active user */
    long insertUser(String username, String password);

    int updateUser(User user);

    int updateUserNameRole(String username, Roles role, long userId);

    int updateUserNameRoleStatus(String username, Roles role, Status status, long userId);

    /*
     * The password field must have a separate method in our implementation
     * As it is not the password that is retrieved from the db
     * but the MD5 hash of the password
     */
    int updateUserPassword(String password, long userId);

    int deleteUser(long userId);
}
