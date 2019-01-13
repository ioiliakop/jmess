package MessagingApp.OldMenus.UserMenu;

import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;

public class ViewInboxMessageOption extends MenuOption {

    private static final String MENU_LINE = "View a message from inbox";
    private              User   owner;

    public ViewInboxMessageOption(int option, User owner) {
        super(option, MENU_LINE);
        this.owner = owner;
    }

    public ViewInboxMessageOption(User owner) {
        this.owner = owner;
    }

    @Override
    public void doAction() {

    }
}
