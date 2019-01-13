package MessagingApp.Menus.AdminOptions.UpdateUserOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

/* This is the admin submenu to update a user */
public class UpdateUserOptionsMenu extends Menu {

    public UpdateUserOptionsMenu(User targetUser) {
        super(targetUser,"","Update " + targetUser.getUsername());
        this.add(new UpdateUsernameOption(targetUser));
        this.add(new UpdatePasswordOption(targetUser));
        this.add(new UpdateUserRoleOption(targetUser));
    }


}
