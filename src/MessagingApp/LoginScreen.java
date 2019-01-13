package MessagingApp;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.AdminOptions.AdminOptionsMenu;
import MessagingApp.Menus.Menu;
import MessagingApp.OldMenus.DeleterMenu.DeleterMenu;
import MessagingApp.OldMenus.EditorMenu.EditorMenu;
import MessagingApp.OldMenus.UserMenu.UserMenu;
import MessagingApp.OldMenus.ViewerMenu.ViewerMenu;

import static MessagingApp.Entities.FinalEntities.Roles.*;
import static MessagingApp.Menus.MenuUtils.*;

public class LoginScreen {

    public static void printLoginScreen() {
        System.out.println("Welcome to our private messaging app");

        do {
            System.out.println("\nPlease enter login credentials");
            String username = inputUsername();
            String password = inputPassword();
            String passwordMD5 = getMD5Of(password);

            UserDAO usrDAO = new MySQLUserDAO();
            User    user   = usrDAO.getActiveUserByUsernameAndPassword(username, passwordMD5);

            if (user == null) {
                System.out.println("\nUsername or password not correct.");
            } else if (user.getRoleId() == ADMIN.ID()) {
//                AdminMenu adMenu = new AdminMenu(user);
//                adMenu.adminMenuExecute();
                Menu adminMenu = new Menu(user);
                adminMenu.add(new AdminOptionsMenu(user));
                adminMenu.execute();
            } else if (user.getRoleId() == DELETER.ID()) {
                DeleterMenu deleterMenu = new DeleterMenu(user);
                deleterMenu.deleterMenuExecute();
            }else if (user.getRoleId() == EDITOR.ID()) {
                EditorMenu editorMenu = new EditorMenu(user);
                editorMenu.editorMenuExecute();
            } else if (user.getRoleId() == VIEWER.ID()) {
                ViewerMenu viewMenu = new ViewerMenu(user);
                viewMenu.viewerMenuExecute();
            } else {
                UserMenu usrMenu = new UserMenu(user);
                usrMenu.userMenuExecute();
            }
        } while (requestConfirmation("\nLogin again?"));
    }

}
