package MessagingApp.Menus.AdminOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Entities.FinalEntities.Status.ACTIVE;
import static MessagingApp.Entities.FinalEntities.Status.DELETED;
import static MessagingApp.Menus.MenuUtils.inputGeneric;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

public class RestoreDeletedUser extends MenuOption {

    public RestoreDeletedUser() {
        super("Restore a deleted user");
    }

    @Override
    public void execute() {
        String username = inputGeneric("Enter username of the deleted user to restore: ");
        UserDAO        usrDAO   = new MySQLUserDAO();
        User           user     = usrDAO.getUser(username);

        if (user != null) {

            if (user.getStatusId() == DELETED.ID()) {

                if (requestConfirmation("User " + user.getUsername() + " will be restored.\nAre you sure? ")) {

                    User deletedUser = this.getUser();
                    deletedUser.setStatusId(ACTIVE.ID());

                    if (usrDAO.updateUser(deletedUser) == 1) System.out.println("User successfully deleted.");
                    else System.out.println("Unknown Error. User was not deleted.");

                } else System.out.println("Operation was cancelled.");

            } else System.out.println("Selected user is not deleted.");

        } else System.out.println("Sorry, username not found.");

        pauseExecution();
    }
}
