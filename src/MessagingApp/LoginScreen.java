package MessagingApp;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.AdminOptions.AdminOptionsMenu;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.RoleOptions.RoleOptionsMenu;
import MessagingApp.Menus.UserOptions.UserOptionsMenu;
import MessagingApp.OldMenus.EditorMenu.EditorMenu;

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
                adminMenu.add(new UserOptionsMenu(user, "User Options"));
                adminMenu.execute();
            } else if (user.getRoleId() == DELETER.ID()) {
//                DeleterMenu deleterMenu = new DeleterMenu(user);
//                deleterMenu.deleterMenuExecute();
                Menu deleterMenu = new Menu(user);
                deleterMenu.add(new RoleOptionsMenu(user));
                deleterMenu.add(new UserOptionsMenu(user,"User Options"));
                deleterMenu.execute();
            }else if (user.getRoleId() == EDITOR.ID()) {
                EditorMenu editorMenu = new EditorMenu(user);
                editorMenu.editorMenuExecute();
            } else if (user.getRoleId() == VIEWER.ID()) {
//                ViewerMenu viewMenu = new ViewerMenu(user);
//                viewMenu.viewerMenuExecute();
                Menu viewerMenu = new Menu(user);
                viewerMenu.add(new RoleOptionsMenu(user));
                viewerMenu.add(new UserOptionsMenu(user,"User Options"));
                viewerMenu.execute();
            } else {
//                UserMenu usrMenu = new UserMenu(user);
//                usrMenu.userMenuExecute();
                UserOptionsMenu userMenu = new UserOptionsMenu(user);
                userMenu.execute();
            }
        } while (requestConfirmation("\nLogin again?"));
    }

}
