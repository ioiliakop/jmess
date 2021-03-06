package MessagingApp.Menus.UserOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.AdminOptions.UpdateUserOptions.UpdatePasswordOption;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.UserOptions.MessageFolderOptions.MessageFolderOptionsMenu;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Entities.MessageFolders.Folder.SENTBOX;
import static MessagingApp.Entities.MessageFolders.Folder.TRASH;

/**
 * Menu presenting all user options
 * This class and all submenu classes extending the Menu class could be omitted
 * The options could be added at menu creation after calling menu default constructor
 * These separate submenu classes were created for better organization of the relative options
 * We gather and select the User options here
 */
public class UserOptionsMenu extends Menu {

    /*
     * Constructor called when UserOptionsMenu is called as a sub menu
     * Typical for user with extra options
     */
    public UserOptionsMenu(User user) {
        super(user, "User Options");
        this.add(new SendMessageOption(user));
        this.add(new ViewActiveUsersOption());
        this.add(new ViewFolderMessagesOption(user, INBOX));
        this.add(new ViewFolderMessagesOption(user, SENTBOX));
        this.add(new ViewFolderMessagesOption(user, TRASH));
        this.add(new MessageFolderOptionsMenu(user));
        this.add(new UpdatePasswordOption(user));
    }

}
