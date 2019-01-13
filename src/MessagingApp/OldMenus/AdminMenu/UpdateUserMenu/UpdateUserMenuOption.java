package MessagingApp.OldMenus.AdminMenu.UpdateUserMenu;

import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;

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
