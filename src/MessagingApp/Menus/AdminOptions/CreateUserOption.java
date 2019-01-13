package MessagingApp.Menus.AdminOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Menus.MenuUtils.inputPassword;
import static MessagingApp.Menus.MenuUtils.inputUsername;
import static MessagingApp.Menus.MenuUtils.pauseExecution;

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
            String password = inputPassword();
            long   userId   = usrDAO.insertUser(username, password);
            if (userId != 0) System.out.println("User successfully created with id " + userId + ".");
            else System.out.println("Unknown Error. User was not created.");
        } else System.out.println("Sorry, entered username is not available.");

        pauseExecution();
    }

}
