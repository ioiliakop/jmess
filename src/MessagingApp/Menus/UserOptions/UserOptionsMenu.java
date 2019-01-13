package MessagingApp.Menus.UserOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

public class UserOptionsMenu extends Menu {

    public UserOptionsMenu(User user) {
        super(user);
        this.add(new SendMessageOption(user));

    }
}
