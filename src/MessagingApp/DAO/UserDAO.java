package MessagingApp.DAO;

import MessagingApp.Entities.Roles.Role;
import MessagingApp.Entities.Statuses;
import MessagingApp.Entities.User;

import java.util.List;

import static MessagingApp.Entities.Statuses.*;

public interface UserDAO {

    User getUser(long userId);

    User getUser(String username);

    /* used for authentication at login screen */
    User getActiveUserByUsernameAndPassword(String username, String password);

    List<User> getAllUsers();

    List<User> getAllUsersByStatus(Status status);

    /* default status is 'ACTIVE' for a created user */
    long insertUser(String username, String password, Role role);

    /* default role and status are set by the db by default, values USER and ACTIVE respectively */
    long insertUser(String username, String password);

    int updateUser(User user);

    int deleteUser(User user);
}
