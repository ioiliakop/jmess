package MessagingApp.Login;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.AdminMenu.AdminMenu;
import MessagingApp.Menus.UserMenu.UserMenu;

import static MessagingApp.Login.Helper.isAdmin;
import static MessagingApp.Menus.MenuUtils.inputPassword;
import static MessagingApp.Menus.MenuUtils.inputUsername;

public class LoginScreen {

    public static void printLoginScreen() {
        System.out.println("Welcome to our private messaging app");
        System.out.println("Please enter login credentials\n");
        String username = inputUsername();
        String password = inputPassword();

        UserDAO usrDAO = new MySQLUserDAO();
        User    user   = usrDAO.getUserByUsernameAndPassword(username, password);

        if (user == null) {
            System.out.println("\nUsername or password not correct.");
        } else if (isAdmin(user)){
            AdminMenu adMenu = new AdminMenu(user);
            adMenu.adminMenuExecute();
        } else {
            UserMenu usrMenu = new UserMenu(user);
            usrMenu.userMenuExecute();
        }
    }

}
