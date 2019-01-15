package MessagingApp.Menus.AdminOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Entities.Statuses.Status.DELETED;
import static MessagingApp.Entities.Statuses.Status.ACTIVE;
import static MessagingApp.Menus.MenuUtils.*;

public class DeleteUserOption extends MenuOption {

    public DeleteUserOption() {
        super("Delete a user account");
    }

    @Override
    public void execute() {

        String  username = inputGeneric("Enter username of the user to be deleted: ");
        UserDAO usrDAO   = new MySQLUserDAO();
        User    user     = usrDAO.getUser(username);

        if (user != null) {

            if (user.getStatusId() == ACTIVE.ID()) {

                if (requestConfirmation("User " + user.getUsername() + " will be deleted.\nAre you sure? ")) {

                    User deletedUser = this.getUser();
                    deletedUser.setStatusId(DELETED.ID());

                    if (usrDAO.updateUser(deletedUser) == 1) System.out.println("User successfully deleted.");
                    else System.out.println("Unknown Error. User was not deleted.");

                } else System.out.println("Operation was cancelled.");

            } else System.out.println("User is already deleted.");

        } else System.out.println("Sorry, username not found.");

        pauseExecution();
    }

}
