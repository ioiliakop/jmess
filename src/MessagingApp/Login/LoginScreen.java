package MessagingApp.Login;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.AdminMenu.AdminMenu;
import MessagingApp.Menus.EditorMenu.EditorMenu;
import MessagingApp.Menus.UserMenu.UserMenu;
import MessagingApp.Menus.ViewerMenu.ViewerMenu;

import static MessagingApp.Entities.Constants.Roles.ADMIN;
import static MessagingApp.Entities.Constants.Roles.EDITOR;
import static MessagingApp.Entities.Constants.Roles.VIEWER;
import static MessagingApp.Login.Helper.isAdmin;
import static MessagingApp.Menus.MenuUtils.inputPassword;
import static MessagingApp.Menus.MenuUtils.inputUsername;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;

public class LoginScreen {

    public static void printLoginScreen() {
        System.out.println("Welcome to our private messaging app");

        do {
            System.out.println("\nPlease enter login credentials\n");
            String username = inputUsername();
            String password = inputPassword();

            UserDAO usrDAO = new MySQLUserDAO();
            User    user   = usrDAO.getUserByUsernameAndPassword(username, password);

            if (user == null) {
                System.out.println("\nUsername or password not correct.");
            } else if (user.getRoleId() == ADMIN.ID()) {
                AdminMenu adMenu = new AdminMenu(user);
                adMenu.adminMenuExecute();
            } else if (user.getRoleId() == EDITOR.ID()) {
                EditorMenu editorMenu = new EditorMenu(user);
                editorMenu.editorMenuExecute();
            } else if (user.getRoleId() == VIEWER.ID()) {
                ViewerMenu viewMenu = new ViewerMenu(user);
                viewMenu.viewerMenuExecute();
            } else {
                UserMenu usrMenu = new UserMenu(user);
                usrMenu.userMenuExecute();
            }
        } while (requestConfirmation("\n\nLogin again?"));
    }

}
