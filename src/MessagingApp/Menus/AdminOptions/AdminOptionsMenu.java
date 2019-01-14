package MessagingApp.Menus.AdminOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

public class AdminOptionsMenu extends Menu {

    public AdminOptionsMenu(User user) {
        super(user,"Admin Options","Admin Options");
        this.add(new CreateUserOption());
        this.add(new UpdateUserOption());
        this.add(new DeleteUserOption());
        this.add(new RestoreDeletedUser());
        this.add(new ViewAllUsersOption());
    }

}
