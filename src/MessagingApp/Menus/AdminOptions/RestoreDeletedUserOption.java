package MessagingApp.Menus.AdminOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Entities.Statuses.Status.ACTIVE;
import static MessagingApp.Entities.Statuses.Status.DELETED;
import static MessagingApp.Menus.MenuUtils.inputGeneric;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

/**
 * Admin has the ability to reactivate a deleted account
 */
public class RestoreDeletedUserOption extends MenuOption {

    public RestoreDeletedUserOption() {
        super("Reactivate a deleted user account");
    }

    @Override
    public void execute() {
        String  username         = inputGeneric("Enter username of the deleted user to restore: ");
        UserDAO usrDAO           = new MySQLUserDAO();
        User    userToBeRestored = usrDAO.getUser(username);

        // validate user exists in db
        if (userToBeRestored != null) {

            // validate user is deleted
            if (userToBeRestored.getStatusId() == DELETED.ID()) {

                if (requestConfirmation("User " + userToBeRestored.getUsername() + " will be restored.\nAre you sure? ")) {

                    userToBeRestored.setStatusId(ACTIVE.ID());

                    if (usrDAO.updateUser(userToBeRestored) == 1) System.out.println("User successfully restored.");
                    else System.out.println("Unknown Error. User was not restored.");

                } else System.out.println("Operation was cancelled.");

            } else System.out.println("Selected user is not deleted.");

        } else System.out.println("Sorry, username not found.");

        pauseExecution();
    }
}
