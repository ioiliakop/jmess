package MessagingApp.Menus.UserOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.UserOptions.DeleteMessageOptions.DeleteMessageOptionsMenu;

import static MessagingApp.Entities.FinalEntities.MessageContainers.INBOX;
import static MessagingApp.Entities.FinalEntities.MessageContainers.SENTBOX;

public class UserOptionsMenu extends Menu {

    /*
     * Constructor called when it is a root menu
     * This happens in the case of simple users (level 1), with no extra options
     */
    public UserOptionsMenu(User user) {
        super(user);
        this.add(new SendMessageOption(user));
        this.add(new ViewContainerMessagesOption(user, INBOX));
        this.add(new ViewContainerMessagesOption(user, SENTBOX));
        this.add(new DeleteMessageOptionsMenu(user));
    }

    /*
     * Constructor called when UserOptionsMenu is called as a sub menu
     * Typical for user with extra options
     */
    public UserOptionsMenu(User user, String menuLine) {
        super(user, menuLine);
        this.add(new SendMessageOption(user));
        this.add(new ViewContainerMessagesOption(user, INBOX));
        this.add(new ViewContainerMessagesOption(user, SENTBOX));
        this.add(new DeleteMessageOptionsMenu(user));
    }


}
