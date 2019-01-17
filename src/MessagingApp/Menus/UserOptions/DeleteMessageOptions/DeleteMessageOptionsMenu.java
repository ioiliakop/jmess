package MessagingApp.Menus.UserOptions.DeleteMessageOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.UserOptions.MoveMessagesSentByToFolderOption;
import MessagingApp.Menus.UserOptions.MoveSpecificMessageFromToFolderOption;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Entities.MessageFolders.Folder.SENTBOX;
import static MessagingApp.Entities.MessageFolders.Folder.TRASH;

/**
 * Options available to a user to delete messages from his personal account's folders only
 * The messages contents not deleted from the db
 * This menu is always a submenu
 */
public class DeleteMessageOptionsMenu extends Menu {

    public DeleteMessageOptionsMenu(User user) {
        super(user, "Delete Message Options");
        this.setMenuTitle("Delete Message Options");
//        this.add(new DeleteSpecificMessagesFromFolderOption(user, INBOX));
//        this.add(new DeleteSpecificMessagesFromFolderOption(user, SENTBOX));
        this.add(new MoveSpecificMessageFromToFolderOption(user, INBOX, TRASH));
        this.add(new MoveSpecificMessageFromToFolderOption(user, SENTBOX, TRASH));
//        this.add(new DeleteMessagesSentByFromFolderOption(user, INBOX));
        this.add(new MoveMessagesSentByToFolderOption(user, INBOX, TRASH, "Delete messages sent by a specific user from " + INBOX));
        this.add(new MoveMessagesSentByToFolderOption(user, TRASH, INBOX, "Restore from " + TRASH + " messages sent by a specific user to " + INBOX));
        this.add(new MoveSpecificMessageFromToFolderOption(user, TRASH, INBOX));
        this.add(new MoveSpecificMessageFromToFolderOption(user, TRASH, SENTBOX));
        this.add(new DeleteAllMessagesFromFolderOption(user, INBOX));
        this.add(new DeleteAllMessagesFromFolderOption(user, SENTBOX));
        this.add(new EmptyFolderOption(user, TRASH));
    }


}
