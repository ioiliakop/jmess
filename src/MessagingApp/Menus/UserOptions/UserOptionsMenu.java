package MessagingApp.Menus.UserOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

public class UserOptionsMenu extends Menu {

    /*
     * Constructor called when it is a root menu
     * This happens in the case of users, with no extra options
     */
    public UserOptionsMenu(User user) {
        super(user);
        this.add(new SendMessageOption(user));
        this.add(new ViewInboxOption(user));
    }

    /*
     * Constructor called when it is called as a sub menu
     * Typical for user with extra options
     */
    public UserOptionsMenu(User user, String menuLine) {
        super(user, menuLine);
        this.add(new SendMessageOption(user));
        this.add(new ViewInboxOption(user));
    }
}
