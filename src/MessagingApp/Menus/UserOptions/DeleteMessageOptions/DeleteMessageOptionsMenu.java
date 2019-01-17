package MessagingApp.Menus.UserOptions.DeleteMessageOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Entities.MessageFolders.Folder.SENTBOX;
import static MessagingApp.Entities.MessageFolders.Folder.TRASH;

/*
* Options available to a user to delete messages from his personal account's folders only
* The messages contents not deleted from the db
* This menu is always a submenu
*/
public class DeleteMessageOptionsMenu extends Menu {

    public DeleteMessageOptionsMenu(User user) {
        super(user, "Delete Message Options");
        this.setMenuTitle("Delete Message Options");
        this.add(new DeleteSpecificMessagesFromFolderOption(user, INBOX));
        this.add(new DeleteSpecificMessagesFromFolderOption(user, SENTBOX));
        this.add(new DeleteAllMessagesFromFolderOption(user, INBOX));
        this.add(new DeleteAllMessagesFromFolderOption(user, SENTBOX));
        this.add(new EmptyFolderOption(user,TRASH));
    }


}
