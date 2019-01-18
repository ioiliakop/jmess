package MessagingApp.Menus.UserOptions.MessageFolderOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Entities.MessageFolders.Folder.SENTBOX;
import static MessagingApp.Entities.MessageFolders.Folder.TRASH;

/**
 * Options available to a user to delete messages from his personal account's folders only
 * The messages contents are not deleted from the db
 * Only their references to the user's folders
 * This menu is always a submenu
 */
public class MessageFolderOptionsMenu extends Menu {

    public MessageFolderOptionsMenu(User user) {
        super(user, "Message Options");
        this.setMenuTitle("Message Options");
        this.add(new MoveSpecificMessageFromToFolderOption(user, INBOX, TRASH));
        this.add(new MoveSpecificMessageFromToFolderOption(user, TRASH, INBOX));
        this.add(new MoveSpecificMessageFromToFolderOption(user, SENTBOX, TRASH));
        this.add(new MoveSpecificMessageFromToFolderOption(user, TRASH, SENTBOX));
        this.add(new MoveMessagesSentByToFolderOption(user, INBOX, TRASH));
        this.add(new MoveMessagesSentByToFolderOption(user, TRASH, INBOX));
        this.add(new MoveAllFolderMessagesToTrashOption(user, INBOX));
        this.add(new MoveAllFolderMessagesToTrashOption(user, SENTBOX));
        this.add(new RestoreAllMessagesFromTrashOption(user));
        this.add(new EmptyFolderOption(user, TRASH));
    }

}
