package MessagingApp.Menus.AdminMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.Services.userExists;

public class DeleteUserOption extends MenuOption {

    private static final String DELETE_USER_MENU_LINE = "Delete a user";

    public DeleteUserOption(int option) {
        super(option, DELETE_USER_MENU_LINE);
    }

/*    @Override
    public void doAction() {
        String username = inputUsername();
        if (usernameExists(username)) {
            UserDAO usrDAO = new MySQLUserDAO();
            USER    user   = usrDAO.getUser(username);
            if (requestConfirmation("USER with following info will be deleted: \n" + user)) {
                usrDAO.deleteUser(user.ID());
                System.out.println("USER deleted.");
            }
        } else System.out.println("Sorry, username not found.");
        pauseExecution();
    }*/

    @Override
    public void doAction() {
        String username = inputUsername();
        UserDAO usrDAO = new MySQLUserDAO();
        if (userExists(usrDAO.getUser(username))) {
            User    user   = usrDAO.getUser(username);

            if (requestConfirmation("Following user will be deleted: " + user + "\nAre you sure?")) {
                if (usrDAO.deleteUser(user.getId())==1) System.out.println("User successfully deleted.");
                else System.out.println("Unknown Error. User was not deleted.");
            }

        } else System.out.println("Sorry, username not found.");
        pauseExecution();
    }
}
