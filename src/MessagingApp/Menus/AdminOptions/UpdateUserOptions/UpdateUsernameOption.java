package MessagingApp.Menus.AdminOptions.UpdateUserOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Menus.MenuUtils.inputUsername;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

/**
 * Only the admin has the option to update a username
 */
public class UpdateUsernameOption extends MenuOption {

    public UpdateUsernameOption(User user) {
        super(user, "Update username");
    }

    @Override
    public void execute() {
        String  newUsername = inputUsername();
        UserDAO usrDAO      = new MySQLUserDAO();
        User    user        = usrDAO.getUser(newUsername);

        // Here we want to validate a user with given username does NOT exist
        if (user == null) {

            if (requestConfirmation("Username will be changed to " + newUsername + ". Proceed? ")) {

                User updatedUser = this.getUser();
                updatedUser.setUsername(newUsername);
                if (usrDAO.updateUser(updatedUser) == 1) System.out.println("Username successfully updated.");
                else System.out.println("Unknown error. User was not updated.");

            } else System.out.println("Update cancelled.");

        } else System.out.println("Sorry, username not available.");

        pauseExecution();

    }
}
