package MessagingApp;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.AdminOptions.AdminOptionsMenu;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.RoleOptions.RoleOptionsMenu;
import MessagingApp.Menus.UserOptions.UserOptionsMenu;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Entities.Roles.Role.*;
import static MessagingApp.Menus.Utils.*;
import static MessagingApp.Menus.MessageServices.unreadMessagesPrompt;

public class LoginScreen {

    public static void printLoginScreen() throws MessagingAppException {
        System.out.println("Welcome to our messaging app");

        do {
            System.out.println("\nPlease enter login credentials");
            String username    = inputGeneric("Enter username: ");
            String password    = inputGeneric("Enter password: ");
            String passwordMD5 = getMD5Of(password);

            UserDAO usrDAO = new MySQLUserDAO();
            User    user   = usrDAO.getActiveUserByUsernameAndPassword(username, passwordMD5);

            // validate user's credentials and execute relative menu depending on user role
            if (user != null) {

                // First prints successful login message along with any unread messages in inbox (if any)
                System.out.println("\nSuccessfully logged in as " + user.getUsername());
                unreadMessagesPrompt(user, INBOX);

                // Then invokes respective menu, depending on user role
                if (user.getRoleId() == ADMIN.ID()) {
                    Menu adminMenu = new Menu(user);
                    adminMenu.add(new AdminOptionsMenu(user));
                    adminMenu.add(new UserOptionsMenu(user));
                    adminMenu.execute();
                } else if (user.getRoleId() == DELETER.ID()) {
                    Menu deleterMenu = new Menu(user);
                    deleterMenu.add(new RoleOptionsMenu(user));
                    deleterMenu.add(new UserOptionsMenu(user));
                    deleterMenu.execute();
                } else if (user.getRoleId() == EDITOR.ID()) {
                    Menu editorMenu = new Menu(user);
                    editorMenu.add(new RoleOptionsMenu(user));
                    editorMenu.add(new UserOptionsMenu(user));
                    editorMenu.execute();
                } else if (user.getRoleId() == VIEWER.ID()) {
                    Menu viewerMenu = new Menu(user);
                    viewerMenu.add(new RoleOptionsMenu(user));
                    viewerMenu.add(new UserOptionsMenu(user));
                    viewerMenu.execute();
                } else if (user.getRoleId() == USER.ID()) {
                    UserOptionsMenu userMenu = new UserOptionsMenu(user);
                    userMenu.setExitPrompt("Exit"); // We call submenu constructor and change exit prompt
                    userMenu.execute();
                } else throw new MessagingAppException("Unknown role ID: " + user.getRoleId());
            } else System.out.println("\nUsername or password not correct");

        } while (requestConfirmation("\nLogin again?"));
    }

}
