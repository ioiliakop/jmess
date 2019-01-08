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
            User    user   = usrDAO.getUser(username);
            if (requestConfirmation("User with following info will be deleted: \n" + user)) {
                usrDAO.deleteUser(user.getId());
                System.out.println("User deleted.");
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

            if (requestConfirmation("User with following info will be deleted: \n" + user)) {
                usrDAO.deleteUser(user.getId());
                System.out.println("User deleted.");
            }

        } else System.out.println("Sorry, username not found.");
        pauseExecution();
    }
}
