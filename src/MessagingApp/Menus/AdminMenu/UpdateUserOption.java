package MessagingApp.Menus.AdminMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;
import MessagingApp.Menus.AdminMenu.UpdateUserMenu.UpdateUserMenu;

import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.Services.printUserInfo;
import static MessagingApp.Menus.Services.usernameExists;

public class UpdateUserOption extends MenuOption {

    private static final String UPDATE_USER_MENU_LINE = "Update a user";

    public UpdateUserOption(int option) {
        super(option, UPDATE_USER_MENU_LINE);
    }

    @Override
    public void doAction() {
        String username = inputUsername("Which user would you like to update? \n");

        if (usernameExists(username)) {
            UserDAO usrDAO = new MySQLUserDAO();
            User    user   = usrDAO.getUser(username);
            System.out.print("USER with following info will be updated:");
            printUserInfo(user);

            // If all is well, the UpdateUser submenu is called and run
            UpdateUserMenu updMenu = new UpdateUserMenu(user);
            updMenu.run();

        } else {
            System.out.println("Sorry, username not found.");
            pauseExecution();
        }
    }

}
