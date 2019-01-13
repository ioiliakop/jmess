package MessagingApp.OldMenus.AdminMenu;

import MessagingApp.Entities.User;
import MessagingApp.OldMenus.Menu;
import MessagingApp.OldMenus.CommonOptions.ViewAllUsersOption;

/**
 * Menu with admin options
 * Currently provides ability to
 * - View all users
 * - Create a user
 * - Delete a user
 * - Update a user
 */
public class AdminMenu {

    private User user;

    public AdminMenu(User user) {
        this.user = user;
    }

    public void adminMenuExecute() {
        Menu adminMenu = new Menu(user);

        adminMenu.add(new ViewAllUsersOption(1));

        adminMenu.add(new CreateUserOption(2));

        adminMenu.add(new DeleteUserOption(3));

        adminMenu.add(new UpdateUserOption(4));

        adminMenu.run();
    }

}


