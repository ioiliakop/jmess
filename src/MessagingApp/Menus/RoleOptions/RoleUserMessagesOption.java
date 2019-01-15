package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.MenuOption;
import MessagingApp.Menus.UserOptions.DeleteMessageOptions.DeleteMessageOptionsMenu;
import MessagingApp.Menus.UserOptions.ViewContainerMessagesOption;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Entities.MessageFolders.Folder.SENTBOX;
import static MessagingApp.Entities.Roles.Role.DELETER;
import static MessagingApp.Entities.Roles.Role.EDITOR;
import static MessagingApp.Entities.Statuses.Status.DELETED;
import static MessagingApp.Menus.MenuUtils.inputGeneric;
import static MessagingApp.Menus.MenuUtils.pauseExecution;

/*
 * This option gives the user who invoked it, the ability to respectively
 * view/edit/delete a specific user's messages, depending on his role (viewer/editor/deleter)
 * Since this option gives access to a specific user's messages
 * We basically call the respective user's menu options as if we were that user
 */
public class RoleUserMessagesOption extends MenuOption {

    public RoleUserMessagesOption(User user) {
        super(user);
        this.setMenuLine(getRoleAbilitiesString() + "a user's messages");
    }

    @Override
    public void execute() {
        System.out.println("Which specific user?");
        String username = inputGeneric("Enter username: ");

        UserDAO usrDAO = new MySQLUserDAO();
        User    user   = usrDAO.getUser(username);

        if (user != null) {
            if (user.getStatusId() != DELETED.ID()) {
                // Each user role's options could be implemented as different submenus
                // But adding all options to the same menu was preferred
                // In order to make navigating through the options easier to the user
                // As all options are related

                // If user is an active user we create and call a new menu
                // which allows us to view selected user's inbox and sentbox
                Menu roleOptions = new Menu(user);
                roleOptions.setMenuTitle(getRoleAbilitiesString() + user.getUsername() + "'s messages");
                roleOptions.setExitPrompt("Back");
                roleOptions.add(new ViewContainerMessagesOption(user, INBOX));
                roleOptions.add(new ViewContainerMessagesOption(user, SENTBOX));
                if (this.getUser().getRoleId() == DELETER.ID()) {
                    roleOptions.add(new DeleteMessageOptionsMenu(user));
                }
                roleOptions.execute();

            } else System.out.println("Sorry. This user has been deleted");
        } else System.out.println("Sorry. Not a registered user");
        pauseExecution();
    }

    /* method that helps correctly present role's abilities in menu lines and titles */
    private String getRoleAbilitiesString(){
        String roleOptions = "View";
        if (this.getUser().getRoleId() == EDITOR.ID()) roleOptions = roleOptions + "/Edit";
        else if (this.getUser().getRoleId() == DELETER.ID()) roleOptions = roleOptions + "/Edit/Delete";
        return roleOptions + " ";
    }
}
