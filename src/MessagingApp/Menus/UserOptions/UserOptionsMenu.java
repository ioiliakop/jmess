package MessagingApp.Menus.UserOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.AdminOptions.UpdateUserOptions.UpdatePasswordOption;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.UserOptions.DeleteMessageOptions.DeleteMessageOptionsMenu;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Entities.MessageFolders.Folder.SENTBOX;
import static MessagingApp.Entities.MessageFolders.Folder.TRASH;

/**
 * Menu presenting all user options
 * This class and all submenu classes extending the Menu class could be omitted
 * The options could be added at menu creation after calling menu default constructor
 * These separate submenu classes were created for better organization of the relative options
 * We gather and select User options here for example
 */
public class UserOptionsMenu extends Menu {

    /*
     * Constructor called when it is a root menu
     * This happens in the case of simple users (level 1), with no extra options
     */
    public UserOptionsMenu(User user) {
        super(user);
        this.add(new SendMessageOption(user));
        this.add(new ViewActiveUsersOption());
        this.add(new ViewFolderMessagesOption(user, INBOX));
        this.add(new ViewFolderMessagesOption(user, SENTBOX));
        this.add(new ViewFolderMessagesOption(user, TRASH));
        this.add(new DeleteMessageOptionsMenu(user));
        this.add(new UpdatePasswordOption(user));
    }

    /*
     * Constructor called when UserOptionsMenu is called as a sub menu
     * Typical for user with extra options
     */
    public UserOptionsMenu(User user, String menuLine) {
        super(user, menuLine);
        this.add(new SendMessageOption(user));
        this.add(new ViewActiveUsersOption());
        this.add(new ViewFolderMessagesOption(user, INBOX));
        this.add(new ViewFolderMessagesOption(user, SENTBOX));
        this.add(new ViewFolderMessagesOption(user, TRASH));
        this.add(new DeleteMessageOptionsMenu(user));
        this.add(new UpdatePasswordOption(user));
    }


    // TODO will implement if time
    /* Method that prompts the user if he has received new messages */
    private void promptForNewMessages(){

    }
}
