package MessagingApp.Menus.AdminOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Menus.MenuUtils.pauseExecution;

public class ViewAllUsersOption extends MenuOption {



    @Override
    public void execute() {
        UserDAO usrDAO = new MySQLUserDAO();
        System.out.println("Users in system: ");
        System.out.println(usrDAO.getAllUsers());
        pauseExecution();
    }
}
