package MessagingApp.Menus.CommonOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Menus.MenuUtils.pauseExecution;

public class ViewAllUsersOption extends MenuOption {

    private static final String VIEW_ALL_USERS_MENU_LINE = "View all users";

    public ViewAllUsersOption(int option) {
        super(option, VIEW_ALL_USERS_MENU_LINE);
    }

    @Override
    public void doAction() {
        UserDAO usrDAO = new MySQLUserDAO();
        System.out.println("Users in system: ");
        System.out.println(usrDAO.getAllUsers());
        pauseExecution();
    }
}
