package MessagingApp.Menus.AdminMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.Services.usernameExists;

public class CreateUserOption extends MenuOption {

    private static final String CREATE_USER_MENU_LINE = "Create a user";

    public CreateUserOption(int option) {
        super(option, CREATE_USER_MENU_LINE);
    }

    @Override
    public void doAction() {
        String username = inputUsername();

        if (!usernameExists(username)) {
            String password = inputPassword();
            UserDAO usrDAO = new MySQLUserDAO();
            long userId = usrDAO.insertUser(username, password);
            System.out.println("USER successfully created with id " + userId + ".");
        }
        else System.out.println("Sorry, entered username is not available.");

        pauseExecution();
    }

}
