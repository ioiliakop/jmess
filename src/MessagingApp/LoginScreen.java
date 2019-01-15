package MessagingApp;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.AdminOptions.AdminOptionsMenu;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.RoleOptions.RoleOptionsMenu;
import MessagingApp.Menus.UserOptions.UserOptionsMenu;

import static MessagingApp.Entities.Roles.Role.*;
import static MessagingApp.Menus.MenuUtils.*;

public class LoginScreen {

    public static void printLoginScreen() {
        System.out.println("Welcome to our messaging app");

        do {
            System.out.println("\nPlease enter login credentials");
            String username    = inputGeneric("Enter username: ");
            String password    = inputGeneric("Enter password: ");
            String passwordMD5 = getMD5Of(password);

            UserDAO usrDAO = new MySQLUserDAO();
            User    user   = usrDAO.getActiveUserByUsernameAndPassword(username, passwordMD5);

            if (user == null) {
                System.out.println("\nUsername or password not correct.");
            } else if (user.getRoleId() == ADMIN.ID()) {
                Menu adminMenu = new Menu(user);
                adminMenu.add(new AdminOptionsMenu(user));
                adminMenu.add(new UserOptionsMenu(user, "User Options"));
                adminMenu.execute();
            } else if (user.getRoleId() == DELETER.ID()) {
                Menu deleterMenu = new Menu(user);
                deleterMenu.add(new RoleOptionsMenu(user));
                deleterMenu.add(new UserOptionsMenu(user, "User Options"));
                deleterMenu.execute();
            } else if (user.getRoleId() == EDITOR.ID()) {
                Menu editorMenu = new Menu(user);
                editorMenu.add(new RoleOptionsMenu(user));
                editorMenu.add(new UserOptionsMenu(user, "User Options"));
                editorMenu.execute();
            } else if (user.getRoleId() == VIEWER.ID()) {
                Menu viewerMenu = new Menu(user);
                viewerMenu.add(new RoleOptionsMenu(user));
                viewerMenu.add(new UserOptionsMenu(user, "User Options"));
                viewerMenu.execute();
            } else {
                UserOptionsMenu userMenu = new UserOptionsMenu(user);
                userMenu.execute();
            }
        } while (requestConfirmation("\nLogin again?"));
    }

}
