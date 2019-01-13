package MessagingApp.Menus.UserOptions.DeleteMessageOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

import static MessagingApp.Entities.FinalEntities.MessageContainers.INBOX;
import static MessagingApp.Entities.FinalEntities.MessageContainers.SENTBOX;

public class DeleteMessageOptionsMenu extends Menu {

    /* This menu is always a submenu */
    public DeleteMessageOptionsMenu(User user) {
        super(user, "Delete Message Options", "Delete Message Options");
        this.add(new DeleteSpecificMessagesFromContainerOption(user, INBOX));
        this.add(new DeleteSpecificMessagesFromContainerOption(user, SENTBOX));
        this.add(new DeleteAllMessagesFromContainerOption(user, INBOX));
        this.add(new DeleteAllMessagesFromContainerOption(user, SENTBOX));
    }


}
