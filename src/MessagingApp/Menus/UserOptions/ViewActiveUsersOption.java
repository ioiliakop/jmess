package MessagingApp.Menus.UserOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Entities.Roles.getRoleFromRoleId;
import static MessagingApp.Entities.Statuses.Status.ACTIVE;
import static MessagingApp.Menus.Utils.pauseExecution;

/**
 * Option that gives any simple user a short list of available users to message
 */
public class ViewActiveUsersOption extends MenuOption {

    public ViewActiveUsersOption() {
        super("View available users");
    }

    @Override
    public void execute() {
        UserDAO    usrDAO         = new MySQLUserDAO();
        List<User> allActiveUsers = usrDAO.getAllUsersByStatus(ACTIVE);

        System.out.println("\nUsers available to message: ");
        for (User user : allActiveUsers) {
            System.out.println("User ID: " + user.getId() + "\t\tUsername: " + user.getUsername() +
                    "\t\tRole: " + getRoleFromRoleId(user.getRoleId()));
        }
        pauseExecution();
    }

}
