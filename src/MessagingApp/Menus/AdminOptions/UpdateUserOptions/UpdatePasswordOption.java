package MessagingApp.Menus.AdminOptions.UpdateUserOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

public class UpdatePasswordOption extends MenuOption {

    public UpdatePasswordOption(User targetUser) {
        super(targetUser, "Update " + targetUser.getUsername() + "'s password");
    }

    @Override
    public void execute() {

    }
}
