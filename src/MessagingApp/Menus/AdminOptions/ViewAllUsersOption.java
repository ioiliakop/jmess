package MessagingApp.Menus.AdminOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.Roles.getRoleFromRoleId;
import static MessagingApp.Entities.Statuses.getStatusFromStatusId;
import static MessagingApp.Menus.Utils.pauseExecution;

/**
 * Gives the admin user information on all users registered
 * Either active ore deleted/deactivated
 */
public class ViewAllUsersOption extends MenuOption {

    public ViewAllUsersOption() {
        super("View all registered users");
    }

    @Override
    public void execute() {
        UserDAO    usrDAO   = new MySQLUserDAO();
        List<User> allUsers = usrDAO.getAllUsers();

        System.out.println("\nUsers in system: ");
        for (User user : allUsers) {
            System.out.println("User ID: " + user.getId() + "\t\tUsername: " + user.getUsername() +
                    "\t\tMD5password: " + user.getPassword() + "\t\tRole: " + getRoleFromRoleId(user.getRoleId()) +
                    "\t\tStatus: " + getStatusFromStatusId(user.getStatusId()));
        }
        pauseExecution();
    }

}
