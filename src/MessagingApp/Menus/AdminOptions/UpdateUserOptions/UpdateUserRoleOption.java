package MessagingApp.Menus.AdminOptions.UpdateUserOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

public class UpdateUserRoleOption extends MenuOption {

    public UpdateUserRoleOption(User targetUser) {
        super(targetUser, "Update " + targetUser.getUsername() + "'s role");
    }

    @Override
    public void execute() {

    }
}
