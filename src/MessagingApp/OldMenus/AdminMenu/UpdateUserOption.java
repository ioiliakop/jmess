package MessagingApp.OldMenus.AdminMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;
import MessagingApp.OldMenus.AdminMenu.UpdateUserMenu.UpdateUserMenu;

import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.OldMenus.Services.printUserInfo;

public class UpdateUserOption extends MenuOption {

    private static final String UPDATE_USER_MENU_LINE = "Update a user";

    public UpdateUserOption(int option) {
        super(option, UPDATE_USER_MENU_LINE);
    }

    @Override
    public void doAction() {
        String username = inputUsername("Which user would you like to update? \n");
        UserDAO usrDAO = new MySQLUserDAO();
        User    user   = usrDAO.getUser(username);

        if (user != null) {
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
