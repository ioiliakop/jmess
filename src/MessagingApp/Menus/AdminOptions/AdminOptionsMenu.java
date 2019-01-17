package MessagingApp.Menus.AdminOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

/**
 * Submenu with all options available to a user with admin role
 */
public class AdminOptionsMenu extends Menu {

    public AdminOptionsMenu(User user) {
        super(user, "Admin Options");
        this.setMenuTitle("Admin Options");
        this.add(new CreateUserOption());
        this.add(new UpdateUserOption());
        this.add(new DeleteUserOption());
        this.add(new RestoreDeletedUserOption());
        this.add(new ViewAllUsersOption());
    }

}
