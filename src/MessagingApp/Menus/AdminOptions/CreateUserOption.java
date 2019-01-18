package MessagingApp.Menus.AdminOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Menus.Utils.*;

/**
 * All new user accounts are assigned level 1 user role, with no special privileges
 * and naturally have 'ACTIVE' status
 * Admin should update the role separately, within the update user submenu
 * if he wishes to elevate user rights
 */
public class CreateUserOption extends MenuOption {

    public CreateUserOption() {
        super("Create a user account");
    }

    @Override
    public void execute() {
        String  username = inputUsername();
        UserDAO usrDAO   = new MySQLUserDAO();
        User    user     = usrDAO.getUser(username);

        if (user == null) {

            String password    = inputPassword();
            String passwordMD5 = getMD5Of(password);
            long   userId      = usrDAO.insertUser(username, passwordMD5);
            if (userId != 0) System.out.println("User successfully created with id " + userId + ".");
            else System.out.println("Unknown Error. User was not created");

        } else System.out.println("Sorry, entered username is not available");

        pauseExecution();
    }

}
