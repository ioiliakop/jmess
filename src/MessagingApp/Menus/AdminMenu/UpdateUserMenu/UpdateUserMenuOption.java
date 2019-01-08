package MessagingApp.Menus.AdminMenu.UpdateUserMenu;

import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

public abstract class UpdateUserMenuOption extends MenuOption {

    private User targetUser;

    public UpdateUserMenuOption(int option, User targetUser) {
        super(option);
        this.targetUser = targetUser;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }
}
