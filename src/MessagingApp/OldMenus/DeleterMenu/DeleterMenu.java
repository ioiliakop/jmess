package MessagingApp.OldMenus.DeleterMenu;

import MessagingApp.Entities.User;
import MessagingApp.OldMenus.CommonOptions.ViewAllUsersOption;
import MessagingApp.OldMenus.EditorMenu.EditUserMessagesOption;
import MessagingApp.OldMenus.Menu;
import MessagingApp.OldMenus.ViewerMenu.ViewUserMessagesOption;

public class DeleterMenu {

    private User user;

    public DeleterMenu(User user) {
        this.user = user;
    }

    public void deleterMenuExecute() {
        Menu deleterMenu = new Menu(user);

        deleterMenu.add(new ViewAllUsersOption(1));

        deleterMenu.add(new ViewUserMessagesOption(2));

        deleterMenu.add(new EditUserMessagesOption(3));

        deleterMenu.add(new DeleteUserMessagesOption(4));

        deleterMenu.run();
    }
}
